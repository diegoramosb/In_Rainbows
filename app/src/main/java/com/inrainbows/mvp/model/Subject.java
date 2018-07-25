package com.inrainbows.mvp.model;

import com.inrainbows.persistence.entities.SubjectEntity;
import com.inrainbows.persistence.entities.SubjectTaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by diego on 31/05/2017.
 * This class represents a subject
 */
public class Subject {

    /**
     * Subject id
     */
    private long id;

    /**
     * Id of the semester that the subject belongs to
     */
    private long semesterId;

    /**
     * Subject name
     */
    private String name;

    /**
     * Amount of credits the subject is worth
     */
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    private double totalHours;

    /**
     * Weekly hours of class
     */
    private double classHours;

    /**
     * Extra expected hours of study (hours without counting class)
     */
    private double extraHours;

    /**
     * Actual studied hours on this day
     */
    private double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    private double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    private double studiedHoursSemester;

    /**
     * Hash table containing tasks.
     */
    private List<SubjectTask> tasks;

    /**
     * Private constructor for builder.
     * @param builder Builder.
     */
    private Subject(SubjectBuilder builder){
        this.id = builder.id;
        this.semesterId = builder.semesterId;
        this.name = builder.name;
        this.credits = builder.credits;
        this.classHours = builder.classHours;

        this.totalHours = calculateTotalHours();
        this.extraHours = calculateExtraHours();

        this.studiedHoursDay = builder.getStudiedHoursDay();
        this.studiedHoursWeek = builder.getStudiedHoursWeek();
        this.studiedHoursSemester = builder.getStudiedHoursSemester();
        this.tasks = builder.getTasks();
    }

    public Subject(SubjectEntity entity){
        if(entity != null) {
            this.id = entity.getId();
            this.semesterId = entity.getSemesterId();
            this.name = entity.getName();
            this.credits = entity.getCredits();

            this.totalHours = entity.getTotalHours();
            this.classHours = entity.getClassHours();
            this.extraHours = entity.getExtraHours();

            this.studiedHoursDay = entity.getStudiedHoursDay();
            this.studiedHoursWeek = entity.getStudiedHoursWeek();
            this.studiedHoursSemester = entity.getStudiedHoursSemester();
            this.tasks = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", semesterId=" + semesterId +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", totalHours=" + totalHours +
                ", classHours=" + classHours +
                ", extraHours=" + extraHours +
                ", studiedHoursDay=" + studiedHoursDay +
                ", studiedHoursWeek=" + studiedHoursWeek +
                ", studiedHoursSemester=" + studiedHoursSemester +
                ", tasks=" + tasks +
                '}';
    }

    /**
     * @return Subject info as a String
     */


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * @return Subject name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the subject name to pName
     *
     * @param pName new subject name
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @return Credits the subject is worth
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Sets the subject credits to pCredits
     *
     * @param pCredits new amount of credits the subject is worth.
     */
    public void setCredits(double pCredits) {
        credits = pCredits;
    }

    /**
     * @return Weekly hours of class of the subject
     */
    public double getClassHours() {
        return classHours;
    }

    /**
     * Sets weekly hours of class to pClassHours
     *
     * @param pClassHours new weekly hours of class
     */
    public void setClassHours(double pClassHours) {
        classHours = pClassHours;
    }

    /**
     * @return Total hours of study that the subject requires, by default credits * 3.
     */
    public double getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the total hours to pTotalHours
     * @param pTotalHours new studied hours
     */
    public void setTotalHours( double pTotalHours ){
        totalHours = pTotalHours;
    }

    /**
     * @return Extra hours of study that the subject requires, meaning totalHours - classHours
     */
    public double getExtraHours() {
        return extraHours;
    }

    /**
     * Sets extraHours to pExtraHours
     *
     * @param pExtraHours new extra hours of the subject
     */
    public void setExtraHours(double pExtraHours) {
        extraHours = pExtraHours;
    }

    /**
     * @param task task
     * @return true if the subject contains the task with name pName
     */
    public boolean containsTask(SubjectTask task) {
        return tasks.contains(task);
    }

    /**
     * @return Studied hours for the current day
     */
    public double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    /**
     * Sets the studied hours for the current day to pStudiedHours
     *
     * @param pStudiedHours new studied hours
     */
    public void setStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay = pStudiedHours;
    }

    /**
     * Increases the studied hours of the current day by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void increaseStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay += pStudiedHours;
    }

    /**
     * @return Studied hours this week in the subject
     */
    public double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    /**
     * Sets the amount of studied hours this week to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours this week
     */
    public void setStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours this week by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void increaseStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek += pStudiedHours;
    }

    /**
     * @return Amount of studiedHours this semester in the subject
     */
    public double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    /**
     * Sets the amount of studied hours of this subject in this semester to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours
     */
    public void setStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours of this subject in this semester by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void increaseStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester += pStudiedHours;
    }

    /**
     * Calculates the amount of total hours of study per week based on the number of credits.
     *
     * @return Total amount of studied hours per week.
     */
    private double calculateTotalHours() {
        return credits * 3;
    }

    /**
     * Calculates the amount of extra study hours per week based on the number of total hours and class hours.
     * @return Amount of extra study hours per week.
     */
    private double calculateExtraHours(){
        return totalHours - classHours;
    }

    /**
     * Changes the task name
     *
     * @param task current task
     * @param pNewName     new task name
     * @throws NoSuchElementException   if the task is not found
     * @throws IllegalArgumentException if either name is not valid
     */
    public void setTaskName(SubjectTask task, String pNewName) throws NoSuchElementException, IllegalArgumentException {
        if( !pNewName.equals("") && pNewName != null ){
            if (tasks.contains(task)) {
                tasks.remove(task);
                task.setName(pNewName);
                tasks.add(task);
            } else
                throw new NoSuchElementException("Task not found.");
        }else
            throw new IllegalArgumentException("Name not valid");
    }

    /**
     * @return an iterable containing the tasks of the subject
     * @throws NoSuchElementException if there are no tasks
     */
    public Iterable<SubjectTask> getTasks() throws NoSuchElementException {
        if(!tasks.isEmpty()){
            return tasks;
        }
        else{
            throw new NoSuchElementException("The subject doesn't have any tasks");
        }
    }

    public void setTasks(List<SubjectTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return an iterable containing only the graded tasks of the subject
     * @throws NoSuchElementException if there are no graded tasks
     */
    public Iterable<SubjectTask> getGradedTasks() throws NoSuchElementException {
        ArrayList<SubjectTask> gradedTasks = new ArrayList<>();
        for( SubjectTask task : tasks ){
            if(task.isGraded()){
                gradedTasks.add(task);
            }
        }
        if(!gradedTasks.isEmpty()){
            return  gradedTasks;
        }
        else{
            throw new NoSuchElementException("There are no graded tasks.");
        }
    }

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     * @throws NoSuchElementException if all tasks are graded
     */
    public Iterable<SubjectTask> getNonGradedTasks() throws NoSuchElementException {
        ArrayList<SubjectTask> nonGradedTasks = new ArrayList<>();
        for( SubjectTask task : tasks ){
            if(!task.isGraded()){
                nonGradedTasks.add(task);
            }
        }
        if(!nonGradedTasks.isEmpty()){
            return  nonGradedTasks;
        }
        else{
            throw new NoSuchElementException("There are no non-graded tasks.");
        }
    }

    /**
     * @return an iterable containing only the delivered tasks of the subject
     * @throws NoSuchElementException if there are not delivered tasks
     */
    public Iterable<SubjectTask> getDeliveredTasks() throws NoSuchElementException {
        ArrayList<SubjectTask> deliveredTasks = new ArrayList<>();
        for( SubjectTask task : tasks ){
            if(task.isDelivered()){
                deliveredTasks.add(task);
            }
        }
        if(!deliveredTasks.isEmpty()){
            return  deliveredTasks;
        }
        else{
            throw new NoSuchElementException("There are no delivered tasks.");
        }
    }

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     * @throws NoSuchElementException if all tasks are delivered
     */
    public Iterable<SubjectTask> getNonDeliveredTasks() throws NoSuchElementException {
        ArrayList<SubjectTask> nonDeliveredTasks = new ArrayList<>();
        for( SubjectTask task : tasks ){
            if(!task.isDelivered()){
                nonDeliveredTasks.add(task);
            }
        }
        if(!nonDeliveredTasks.isEmpty()){
            return  nonDeliveredTasks;
        }
        else{
            throw new NoSuchElementException("There are no non-delivered tasks.");
        }
    }

    /**
     * @param pTaskName name of the task
     * @return Task with name pTaskName
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public SubjectTask getTask(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        if( !pTaskName.equals("") ) {
            for(SubjectTask task : tasks) {
                if (task.getName().equals(pTaskName)) {
                    return task;
                }
            }
            throw new NoSuchElementException("Task not found");
        }
        else
            throw new IllegalArgumentException("Task name not valid");
    }

    /**
     * Adds a new task to the subject. It gets marked as non-delivered and non-graded by default
     * @param pTask new task to be added
     */
    public void addTask(SubjectTask pTask) {
        tasks.add(pTask);
    }

    /**
     * Deletes task with name pTaskName from the task list of the subject
     * @param task the task to be deleted
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void deleteTask(SubjectTask task) throws NoSuchElementException, IllegalArgumentException {
        try {
            tasks.remove(task);
        }catch (NullPointerException e){
            throw new IllegalArgumentException("Trying to delete a null task");
        }
    }

    /**
     * Marks the task with name pTaskName to done
     *
     * @param pTaskName name of the task
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void markAsDone(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setDone(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the done status of the task with name pTaskName to pDone
     *
     * @param pTaskName name of the task
     * @param pDone     new done status
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void setDone(String pTaskName, boolean pDone) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setDone(pDone);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Marks the task with name pTaskName to delivered
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void markAsDelivered(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setDelivered(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pDelivered new delivered status
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void setDelivered(String pTaskName, boolean pDelivered) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setDelivered(pDelivered);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Marks the task with name pTaskName to graded
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void markAsGraded(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setGraded(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }
    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pGraded new GradedStatus
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    public void setGraded(String pTaskName, boolean pGraded) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setGraded(pGraded);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the grade of the task with name pTaskName to pGrade
     *
     * @param pTaskName name of the task
     * @param pGrade    new grade
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name or grade are not valid
     */
    public void setGrade(String pTaskName, double pGrade) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setGrade(pGrade);
        }catch (IllegalArgumentException | AssertionError e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * @return percentage of tasks of the subject that have been graded
     */
    public double getGradedTasksPercentage() {
        double ans = 0;
        //Adds the percentage of all graded tasks to the answer
        try {
            for (SubjectTask currentTask : getGradedTasks()) {
                ans += currentTask.getPercentage();
            }
            return ans;
        }catch (NoSuchElementException ignored){}
        return ans;
    }

    /**
     * Retorna la nota actual de la materia. El cálculo se hace así: Si la suma de las notas
     * entregadas es 100 se calcula calificacionActual * (porcentaje / 100). Sino, se calcula
     * calificacionActual * (porcentaje / porcentajeNotasEntregadas).
     * @return ITask actual de la materia (solo de las notas entregadas)
     */
    public double currentGrade() {
        double ans = 0;
        double gradedPercentage = getGradedTasksPercentage();
        try {
            Iterable<SubjectTask> gradedTasks = getGradedTasks();
            if( gradedPercentage >= 100.0 ){
                for( SubjectTask currentTask : gradedTasks )
                    ans += (currentTask.getGrade() * currentTask.getPercentage()) / 100.0;
            }
            else{
                for( SubjectTask currentTask : gradedTasks )
                    ans += (currentTask.getGrade() * currentTask.getPercentage()) / gradedPercentage;
            }
        }catch (NoSuchElementException ignored){
            return ans;
        }
        return ans;
    }

    public SubjectEntity toEntity(long semesterId){
        return new SubjectEntity.SubjectEntityBuilder(id, name, credits, classHours, semesterId).build();
    }

    public List<SubjectTaskEntity> subjectTasksToEntity(){
        ArrayList<SubjectTaskEntity> ans = new ArrayList<>();
        for(SubjectTask task : tasks){
            ans.add(new SubjectTaskEntity.SubjectTaskEntityBuilder(task.getId(), task.getName(), task.getPercentage(), id).build());
        }
        return ans;
    }

    public SubjectEntity toEntity() {
        return new SubjectEntity(id, name, credits, totalHours, classHours, extraHours,
                studiedHoursDay, studiedHoursWeek, studiedHoursSemester, semesterId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (Double.compare(subject.credits, credits) != 0) return false;
        if (Double.compare(subject.totalHours, totalHours) != 0) return false;
        if (Double.compare(subject.classHours, classHours) != 0) return false;
        if (Double.compare(subject.extraHours, extraHours) != 0) return false;
        if (Double.compare(subject.studiedHoursDay, studiedHoursDay) != 0) return false;
        if (Double.compare(subject.studiedHoursWeek, studiedHoursWeek) != 0) return false;
        if (Double.compare(subject.studiedHoursSemester, studiedHoursSemester) != 0) return false;
        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(credits);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(classHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(extraHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursWeek);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursSemester);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class SubjectBuilder {

        private long id;

        private long semesterId;

        private String name;

        private double credits;

        private double totalHours;

        private double classHours;

        private double extraHours;

        private double studiedHoursDay;

        private double studiedHoursWeek;

        private double studiedHoursSemester;

        private List<SubjectTask> tasks;

        public SubjectBuilder(long id, String name, double credits, double classHours, long semesterId) {
            this.id = id;
            this.semesterId = semesterId;
            this.name = name;
            this.credits = credits;
            this.classHours = classHours;
            tasks = new ArrayList<>();
        }

        public double getTotalHours() {
            return totalHours;
        }

        public SubjectBuilder setTotalHours(double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public double getExtraHours() {
            return extraHours;
        }

        public SubjectBuilder setExtraHours(double extraHours) {
            this.extraHours = extraHours;
            return this;
        }

        public double getStudiedHoursDay() {
            return studiedHoursDay;
        }

        public SubjectBuilder setStudiedHoursDay(double studiedHoursDay) {
            this.studiedHoursDay = studiedHoursDay;
            return this;
        }

        public double getStudiedHoursWeek() {
            return studiedHoursWeek;
        }

        public SubjectBuilder setStudiedHoursWeek(double studiedHoursWeek) {
            this.studiedHoursWeek = studiedHoursWeek;
            return this;
        }

        public double getStudiedHoursSemester() {
            return studiedHoursSemester;
        }

        public SubjectBuilder setStudiedHoursSemester(double studiedHoursSemester) {
            this.studiedHoursSemester = studiedHoursSemester;
            return this;
        }

        public List<SubjectTask> getTasks() {
            return tasks;
        }

        public SubjectBuilder setTasks(List<SubjectTask> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Subject build(){
            return new Subject(this);
        }
    }
}
