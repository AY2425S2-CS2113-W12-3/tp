
package seedu.fintrack;

import java.util.Calendar;
import java.util.Date;

public class RecurringExpense extends Expense{
    private String frequency; // Weekly, Monthly, Yearly
    private Date startDate;
    private Date lastProcessedDate;


    public RecurringExpense(int amount, String category, String frequency, String description,
                            Date startDate, Date date) {
        super(amount, category, description, date);
        this.frequency = frequency;
        this.startDate = startDate;
        this.lastProcessedDate = date;
    }


    public String getFrequency() {
        return frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getLastProcessedDate() {
        return lastProcessedDate;
    }

    public void setLastProcessedDate(Date lastProcessedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastProcessedDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.lastProcessedDate = calendar.getTime();
        System.out.println(lastProcessedDate);
    }

}
