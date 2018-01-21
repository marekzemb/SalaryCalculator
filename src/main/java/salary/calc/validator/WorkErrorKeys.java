package salary.calc.validator;

interface WorkErrorKeys {
    String EMPTY_NAME = "EMPTY_NAME";
    String NEGATIVE_HOURLY_WAGE = "NEGATIVE_HOURLY_WAGE";
    String NEGATIVE_TAX_RATE = "NEGATIVE_TAX_RATE";
    String TOO_BIG_TAX_RATE = "TOO_BIG_TAX_RATE";
    String NEGATIVE_EMPLOYEE_ID = "NEGATIVE_EMPLOYEE_ID";
    String START_TIME_AFTER_FINISH_TIME = "START_TIME_AFTER_FINISH_TIME";
    String START_TIME_EQUAL_FINISH_TIME = "START_TIME_EQUAL_FINISH_TIME";
}
