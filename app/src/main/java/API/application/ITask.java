package api.application;

/**
 * @author Diego
 * Created by diego on 11/12/2017.
 * Interface for tasks
 */
public interface ITask {
    /**
     * @return task name
     */
    String getName();

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    void setName( String pName );

    /**
     * @return true if the task has been completed, false otherwise
     */
    boolean getDone();

    /**
     * Sets the done status to pDone
     * @param pDone new done status
     */
    void setDone( boolean pDone );

    /**
     * @return Task tag
     */
    String getTag();

    /**
     * Sets the task tag to pTag
     * @param pTag tag to be set
     */
    void setTag( String pTag );

    /**
     * @return task grade
     */
    double getGrade();

    /**
     * Sets the task grade to pGrade
     */
    void setGrade( double pGrade );

    /**
     * @return percentage of the Subject that the task is worth
     */
    double getPercentage();

    /**
     * Sets the percentage of the task to pPercentage
     * @param pPercentage new percentage of the task
     */
    void setPercentage( double pPercentage );

    /**
     * @return true if the task has been graded, false otherwise
     */
    boolean getGraded();

    /**
     * Changes the graded status of the task
     * @param pGraded new graded status
     */
    void setGraded( boolean pGraded );

    /**
     * @return true if the task was delivered by the student, false otherwise
     */
    boolean getDelivered();

    /**
     * Changes the delivered status of this task to pDelivered
     * @param pDelivered new delivered status
     */
    void setDelivered( boolean pDelivered );
}
