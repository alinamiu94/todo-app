package org.example.repository;

import org.example.enums.Status;
import org.example.model.Task;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRepository extends Repository{

    private static final String SELECT_TASK_BY_ID_QUERY = "select * from Task where id = %s;";
    private static final String SELECT_FROM_TASK = "select * from Task;";
    private static final String INSERT_TASK = "INSERT INTO TASK(ID, NAME, DESCRIPTION,USER_ID, " +
            "DUE_DATE,STATUS,CREATED_AT) VALUES (nextval('task_id_seq'), '%s', '%s', %s, " +
            "to_timestamp('%s', 'dd-mm-yyyy'), '%s', " +
            "to_timestamp('%s', 'dd-mm-yyyy') " +
            ");";
    private static final String DELETE_TASK_BY_ID = "delete from task where id=%s;";
    private static final String UPDATE_STATUS_BY_ID = "update task set status = '%s' where id = %s;";
    private static final String SELECT_FROM_TASK_BY_USER_ID = "select * from task where user_id = %s;";
    private static final String UPDATE_FINISHED_AT_BY_TASK_ID = "update task set finished_at = " +
            "to_timestamp('%s', 'dd-mm-yyyy')" +
            " where id = %s;";


    public Task getTaskById(int id) {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_TASK_BY_ID_QUERY,id));
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            int taskId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            int userId = resultSet.getInt(4);
            Date dueDate = resultSet.getDate(5);
            String status = resultSet.getString(6);
            Date createdAt = resultSet.getDate(7);
            Date finishedAt = resultSet.getDate(8);

            return new Task(taskId,name,description,userId,dueDate, Status.valueOf(status),createdAt,finishedAt);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Task> getTasks() {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(SELECT_FROM_TASK);
            ResultSet resultSet = statement.executeQuery();

            List<Task> taskList = new ArrayList<>();
            while(resultSet.next()) {
                int taskId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                int userId = resultSet.getInt(4);
                Date dueDate = resultSet.getDate(5);
                String status = resultSet.getString(6);
                Date createdAt = resultSet.getDate(7);
                Date finishedAt = resultSet.getDate(8);

                Task task = new Task(taskId,name,description,userId,dueDate,Status.valueOf(status),createdAt,finishedAt);
                taskList.add(task);

            }
            return taskList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTask(Task task){

        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(INSERT_TASK, task.getName(),
                    task.getDescription(), task.getUserId(), getDateInValidFormat(task.getDueDate()),
                    task.getStatus(), getDateInValidFormat(task.getCreatedAt())));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getDateInValidFormat(Date date) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public void deleteTask(int id) {

        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(DELETE_TASK_BY_ID,id));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateStatus(Status status, int id){
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement(String.format(UPDATE_STATUS_BY_ID,status,id));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTasksBYUserId(int id) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(String.format(SELECT_FROM_TASK_BY_USER_ID, id));
            ResultSet resultSet = statement.executeQuery();

            List<Task> taskList = new ArrayList<>();
            while (resultSet.next()) {
                int taskId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                int userId = resultSet.getInt(4);
                Date dueDate = resultSet.getDate(5);
                String status = resultSet.getString(6);
                Date createdAt = resultSet.getDate(7);
                Date finishedAt = resultSet.getDate(8);

                Task task = new Task(taskId, name, description, userId, dueDate, Status.valueOf(status), createdAt, finishedAt);
                taskList.add(task);

            }
            return taskList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateFinishedAtByTaskId(Date date, int taskId) {
        try(Connection conn = connect()){
            PreparedStatement statement = conn.prepareStatement
                    (String.format(UPDATE_FINISHED_AT_BY_TASK_ID,getDateInValidFormat(date),taskId));
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
