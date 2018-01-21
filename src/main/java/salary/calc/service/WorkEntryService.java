package salary.calc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salary.calc.dao.EmployeeDao;
import salary.calc.dao.WorkEntryDao;
import salary.calc.domain.WorkEntry;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class WorkEntryService {

    @Autowired
    private WorkEntryDao workEntryDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<WorkEntry> getAllWorkEntries() {
        return workEntryDao.getAllWorkEntries();
    }

    public Optional<WorkEntry> getEntryById(long entryId) {
        return workEntryDao.getEntryById(entryId);
    }

    public void addWorkEntry(WorkEntry workEntry) {
        workEntryDao.addWorkEntry(workEntry);
    }

    public void deleteWorkEntry(int entryId) {
        workEntryDao.deleteWorkEntry(entryId);
    }

    public void updateWorkEntry(long entryId, WorkEntry workEntry) {
        workEntryDao.updateWorkEntry(entryId, workEntry);
    }

    public List<WorkEntry> getAllEntriesByEmployeeId(long id) {
        return workEntryDao.getAllEntriesByEmployeeId(id);
    }

    public List<WorkEntry> getMonthlyEmployeeEntries(long id, int month, int year) {
        return workEntryDao.getMonthlyEmployeeEntries(id, month, year);
    }

    public double getMonthlyGrossSalary(long id, int month, int year) {
        return this.getEmployeeGrossSalaryFromHours(this.getHoursFromEntriesList(this.getMonthlyEmployeeEntries(id, month, year)), id);
    }

    public double getMonthlyNetSalary(long id, int month, int year) {
        return (this.getMonthlyGrossSalary(id, month, year) * (1 - employeeDao.getEmployeeById(id).get().getTaxRate()));
    }

    public double getEmployeeTaxYearGrossSalary(long id, int yearStart, int yearFinish) {
        return this.getEmployeeGrossSalaryFromHours(this.getHoursFromEntriesList(workEntryDao.getEmployeeTaxYearWorkEntries(id, yearStart, yearFinish)), id);
    }

    public double getHoursFromEntriesList(List<WorkEntry> entriesList) {
        double hourSum = 0;
        for (WorkEntry entry : entriesList) {
            System.out.println(entry.toString());
            hourSum += ChronoUnit.MINUTES.between(entry.getStartTime(), entry.getFinishTime());
        }
        return hourSum / 60;
    }

    private double getEmployeeGrossSalaryFromHours(double hours, long id) {
        return hours * employeeDao.getEmployeeById(id).get().getHourlyWage();
    }
}
