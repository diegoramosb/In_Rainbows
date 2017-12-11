package API.application;

/**
 * Created by diego on 11/12/2017.
 * Interface for tasks
 */
public interface ITask {
    /**
     * @returns task name
     */
   String getName();

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
     */
   void setGraded();
}
