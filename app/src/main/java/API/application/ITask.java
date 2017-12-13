package API.application;

/**
 * @author Diego
 * Created by diego on 11/12/2017.
 * Interface for tasks
 */
public interface ITask {
    /**
     * @returns task name
     */
    String getName();

    /**
     * @return task grade
     */
    double getGrade();

    /**
     * Sets the task grade to pGrade
     * @return
     */
    void setGrade( double pGrade );

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    void setName( String pName );

    /**
     * @returns percentage of the Subject that the task is worth
     */
    double getPercentage();

    /**
     * Sets the percentage of the task to pPercentage
     * @param pPercentage new percentage of the task
     */
    void setPercentage( double pPercentage );

    /**
     * @returns true if the task has been graded, false otherwise
     */
    boolean getGraded();

    /**
     * Changes the graded status of the task
     * @param pGraded new graded status
     */
    void setGraded( boolean pGraded );

    /**
     * @returns true if the task was delivered by the student, false otherwise
     */
    boolean getDelivered();

    /**
     * Changes the delivered status of this task to pDelivered
     * @param pDelivered new delivered status
     */
    void setDelivered( boolean pDelivered );
}
