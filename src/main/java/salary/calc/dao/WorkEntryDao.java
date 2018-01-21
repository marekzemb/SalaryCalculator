package salary.calc.dao;


import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import salary.calc.domain.WorkEntry;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.ENTRY;

@Repository
public class WorkEntryDao {

    @Autowired
    private DSLContext dslContext;

    public List<WorkEntry> getAllWorkEntries() {
        return dslContext.selectFrom(ENTRY).fetchInto(WorkEntry.class);
    }

    public Optional<WorkEntry> getEntryById(long entryId){
        return dslContext.selectFrom(ENTRY)
                .where(ENTRY.ENTRY_ID.eq(entryId))
                .fetchOptionalInto(WorkEntry.class);
    }

    public void addWorkEntry(WorkEntry workEntry) {
        dslContext.insertInto(ENTRY, ENTRY.EMPLOYEE_ID, ENTRY.START_TIME, ENTRY.FINISH_TIME)
                .values(workEntry.getEmployeeId(), Timestamp.valueOf(workEntry.getStartTime()), Timestamp.valueOf(workEntry.getFinishTime()))
                .execute();
    }

    public void deleteWorkEntry(long id) {
        dslContext.deleteFrom(ENTRY)
                .where(ENTRY.ENTRY_ID.eq(id))
                .execute();
    }

    public void updateWorkEntry(long id, WorkEntry workEntry) {
        dslContext.update(ENTRY)
                .set(ENTRY.EMPLOYEE_ID, workEntry.getEmployeeId())
                .set(ENTRY.START_TIME,  Timestamp.valueOf(workEntry.getStartTime()))
                .set(ENTRY.FINISH_TIME, Timestamp.valueOf(workEntry.getFinishTime()))
                .where(ENTRY.ENTRY_ID.eq(id))
                .execute();
    }

    public List<WorkEntry> getAllEntriesByEmployeeId(long id) {
        return dslContext.selectFrom(ENTRY).where(ENTRY.EMPLOYEE_ID.eq(id)).fetchInto(WorkEntry.class);
    }

    public List<WorkEntry> getMonthlyEmployeeEntries(long id, int month, int year) {
        LocalDateTime from = LocalDateTime.of(year, month, 1, 0 ,0);
        LocalDateTime to = from.plusMonths(1);
        return dslContext.selectFrom(ENTRY)
                .where(ENTRY.EMPLOYEE_ID.eq(id))
                .and(ENTRY.START_TIME.between(Timestamp.valueOf(from), Timestamp.valueOf(to)))
                .fetchInto(WorkEntry.class);
    }

    public List<WorkEntry> getEmployeeTaxYearWorkEntries(long id, int yearStart, int yearFinish) {
        Timestamp from = Timestamp.valueOf(LocalDateTime.of(yearStart, 4, 1, 0, 0));
        Timestamp to  = Timestamp.valueOf(LocalDateTime.of(yearFinish, 3, 31, 23, 59));
        return dslContext.selectFrom(ENTRY)
                .where(ENTRY.EMPLOYEE_ID.eq(id))
                .and(ENTRY.START_TIME.between(from, to))
                .fetchInto(WorkEntry.class);
    }
}

