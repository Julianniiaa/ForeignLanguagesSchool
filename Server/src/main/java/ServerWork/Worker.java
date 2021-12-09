package ServerWork;

import DB.SQLFactory;
import SchoolOrg.*;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Worker implements Runnable {
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
//                Students student = (Students)sois.readObject();
//                System.out.println(student.toString());
                System.out.println("Получение команды от клиента...");
                String choice = sois.readObject().toString();
                System.out.println(choice);
                System.out.println("Команда получена");
                switch (choice) {
                    case "adminInf" -> {
                        System.out.println("Запрос к БД на получение информации об администраторе: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Admin> infList = sqlFactory.getAdmin().get();
                        System.out.println(infList.toString());
                        soos.writeObject(infList);
                    }
                    case "studInf" -> {
                        System.out.println("Запрос к БД на получение информации о студентах: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Students> studentsList = sqlFactory.getStudents().get();
                        System.out.println(studentsList.toString());
                        soos.writeObject(studentsList);
                    }
                    case "findStudent" -> {
                        System.out.println("Запрос к БД на поиск студента: " + clientSocket.getInetAddress().toString());
                        Students st = (Students) sois.readObject();
                        System.out.println(st.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Students> studList = sqlFactory.getStudents().findStudent(st);
                        System.out.println(studList.toString());
                        soos.writeObject(studList);
                    }
                    case "registrationTeacher" -> {
                        System.out.println("Запрос к БД на проверку пользователя(таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Teacher teacher = (Teacher) sois.readObject();
                        System.out.println(teacher.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getTeachers().insert(teacher)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "teacherInf" -> {
                        System.out.println("Запрос к БД на проверку преподавателя (таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Teacher> teachers = sqlFactory.getTeachers().getTeacher(r);
                        System.out.println(teachers.toString());
                        soos.writeObject(teachers);
                    }
                    case "teacherTimetable" -> {
                        System.out.println("Запрос к БД на проверку преподавателя (таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Groups> groups = sqlFactory.getGroups().getpersTimetable(r);
                        System.out.println(groups.toString());
                        soos.writeObject(groups);
                    }
                    case "studentsTeacherTimetable" -> {
                        System.out.println("Запрос к БД на поиск преподавателя (таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Role role = sqlFactory.getTeachers().getIdByStudents(r);

                        ArrayList<Groups> groups = sqlFactory.getGroups().getpersTimetable(role);
                        System.out.println(groups.toString());
                        soos.writeObject(groups);
                    }
                    case "studentTimetable" -> {
                        System.out.println("Запрос к БД на поиск расписания студента: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();

                        Students st = sqlFactory.getStudents().getGrNumber(r);
                        Groups gr = new Groups();
                        gr.setGrNumber(st.getGroup());

                        ArrayList<Groups> groupsList = sqlFactory.getGroups().find(gr);
                        System.out.println(groupsList.toString());
                        soos.writeObject(groupsList);
                    }
                    case "addTimetable" -> {
                        System.out.println("Выполняется добавление расписания...");
                        Timetable timetable = (Timetable) sois.readObject();
                        System.out.println(timetable.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getTimetable().insert(timetable)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при записи расписания");
                        }
                    }
                    case "addCourse" -> {
                        System.out.println("Выполняется добавление курса...");
                        Courses course = (Courses) sois.readObject();
                        System.out.println(course.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getCourse().insertCourse(course)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при записи курса");
                        }
                    }
                    case "deleteStud" -> {
                        System.out.println("Выполняется удаление студента...");
                        Students students = (Students) sois.readObject();
                        System.out.println(students.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getStudents().deleteStud(students)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении студента");
                        }
                    }
                    case "changeTeacher" -> {
                        System.out.println("Запрос к БД на изменение пользователя(таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Teacher teacher = (Teacher) sois.readObject();
                        System.out.println(teacher.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getTeachers().changeTeacher(teacher)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "changeTimetable" -> {
                        System.out.println("Выполняется изменение расписания...");
                        Timetable timetable = (Timetable) sois.readObject();
                        System.out.println(timetable.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getTimetable().change(timetable)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при изменении расписания");
                        }
                    }
                    case "showGrTimetable" -> {
                        System.out.println("Запрос к БД на получение расписания группы: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Groups> groupsList = sqlFactory.getGroups().get();
                        System.out.println(groupsList.toString());
                        soos.writeObject(groupsList);
                    }
                    case "findGrTimetable" -> {
                        System.out.println("Запрос к БД на поиск расписания группы: " + clientSocket.getInetAddress().toString());
                        Groups gr = (Groups) sois.readObject();
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Groups> groupsList = sqlFactory.getGroups().find(gr);
                        System.out.println(groupsList.toString());
                        soos.writeObject(groupsList);
                    }
                    case "showCourses" -> {
                        System.out.println("Запрос к БД на получение доступных курсов: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Courses> courseList = sqlFactory.getCourse().get();
                        System.out.println(courseList.toString());
                        soos.writeObject(courseList);
                    }
                    case "findCourses" -> {
                        System.out.println("Запрос к БД на поиск курсов: " + clientSocket.getInetAddress().toString());
                        Courses c = (Courses) sois.readObject();
                        System.out.println(c.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Courses> courses = sqlFactory.getCourse().find(c);
                        System.out.println(courses.toString());
                        soos.writeObject(courses);
                    }
                    case "showGrTeacher" -> {
                        System.out.println("Запрос к БД на получение расписания преподавателя: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Groups> groupsList = sqlFactory.getGroups().getTeacher();
                        System.out.println(groupsList.toString());
                        soos.writeObject(groupsList);
                    }
                    case "findTTimetable" -> {
                        System.out.println("Запрос к БД на поиск расписания преподавателя: " + clientSocket.getInetAddress().toString());
                        Groups t = (Groups) sois.readObject();
                        System.out.println(t.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Groups> groupsList = sqlFactory.getGroups().findTeacher(t);
                        System.out.println(groupsList.toString());
                        soos.writeObject(groupsList);
                    }
                    case "registrationStudent" -> {
                        System.out.println("Запрос к БД на проверку пользователя(таблица students), клиент: " + clientSocket.getInetAddress().toString());
                        Students student = (Students) sois.readObject();
                        System.out.println(student.toString());

                        SQLFactory sqlFactory = new SQLFactory();
                        Role r = sqlFactory.getStudents().insert(student);
                        System.out.println((r.toString()));

                        if (r.getId() != 0 && r.getRole() != "") {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else {
                            soos.writeObject("This user is already existed");
                        }
                    }
                    case "authorization" -> {
                        System.out.println("Выполняется авторизация пользователя....");
                        Authorization auth = (Authorization) sois.readObject();
                        System.out.println(auth.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Role r = sqlFactory.getRole().getRole(auth);
                        System.out.println(r.toString());
                        if (r.getId() != 0 && r.getRole() != "") {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else
                            soos.writeObject("There is no data!");
                    }
                    case "studentInf" -> {
                        System.out.println("Запрос к БД на проверку студента (таблица students), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Students students = sqlFactory.getStudents().getStudent(r);
                        System.out.println(students.toString());
                        soos.writeObject(students);
                    }
                    case "regCourse" -> {
                        System.out.println("Выполняется регистрация пользователя на курс...");
                        Courses course = (Courses) sois.readObject();
                        System.out.println(course.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getCourse().registration(course)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при записи на курс");
                        }
                    }
                    case "studMarks" -> {
                        System.out.println("Запрос к БД на получение информации о студентах: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());
                        Role r1 = new Role();
                        SQLFactory sqlFactory = new SQLFactory();
                        r1 = sqlFactory.getTeachers().getIdByTeacher(r);
                        System.out.println(r1.toString());
                        ArrayList<Students> st = sqlFactory.getStudents().getMarks(r1);
                        soos.writeObject(st);
                    }
                    case "addMarks" -> {
                        System.out.println("Запрос к БД на получение добавление отметок студенту: " + clientSocket.getInetAddress().toString());
                        Students s = (Students) sois.readObject();
                        Role r = (Role) sois.readObject();
                        Students s1 = new Students();
                        System.out.println(s.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        s1 = sqlFactory.getStudents().getMark(s, r);
                        System.out.println(s1.toString());

                        try {
                            Integer.parseInt(s1.getLogin());
                            soos.writeObject("OK");

                            if (s1.getColMarks() == 0) {
                                s1.setAverageMark(s.getAverageMark());
                                s1.setColMarks(1);
                            } else {
                                int col = s1.getColMarks();
                                double avg = Double.parseDouble(s1.getAverageMark()), average;
                                average = (avg * col + Double.parseDouble((s.getAverageMark()))) / (col + 1);
                                s1.setAverageMark(String.valueOf(average));
                                s1.setColMarks(col + 1);
                            }
                            sqlFactory.getStudents().setMarks(s1);
                        } catch (NumberFormatException e) {
                            soos.writeObject("Неверный номер группы");
                        }
                    }
                    case "getDiagrReceive" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().get();

                        ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                        for (Receive r : receives) {
                            data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                    r.getLanguage(), r.getBalance()));
                        }

                        soos.writeObject(data);
                    }
                    case "getChartReceive" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().getChart();

                        ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                        for (Receive r : receives) {
                            data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                    r.getLanguage(), r.getBalance()));
                        }

                        soos.writeObject(data);
                    }
                    case "getChartProgress" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Students> students = sqlFactory.getStudents().getProgress();
                        System.out.println(students);
                        ArrayList<AbstractMap.SimpleEntry<String, Double>> data = new ArrayList<>();
                        for (Students s : students) {
                            try {
                                data.add(new AbstractMap.SimpleEntry<String, Double>(
                                        s.getLogin(), Double.parseDouble(s.getAverageMark())));
                            } catch (Exception e) {
                                System.out.println("null");
                            }
                        }
                        System.out.println(data.toString());
                        soos.writeObject(data);
                    }
                    case "writeReceiveReport" -> {
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().get();

                        if (receives.size() == 0)
                            soos.writeObject("Ничего нет");
                        else {

                            BufferedWriter outputWriter = null;
                            outputWriter = new BufferedWriter(new FileWriter("profit"));
                            outputWriter.write("Прибыль по курсам:\n");
                            for (Receive r : receives) {
                                outputWriter.write(r.getBalance() + "   " + r.getLanguage());
                                outputWriter.newLine();
                            }
                            outputWriter.flush();
                            outputWriter.close();

                            soos.writeObject("OK");
                        }
                    }
                    case "writeProgressReport" -> {
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Students> students = sqlFactory.getStudents().getProgress();

                        if (students.size() == 0)
                            soos.writeObject("Ничего нет");
                        else {

                            BufferedWriter outputWriter = null;
                            outputWriter = new BufferedWriter(new FileWriter("progress"));
                            outputWriter.write("Рейтинг студентов:\n");
                            for (Students s : students) {
                                outputWriter.write(s.getLogin() + "   " + s.getAverageMark());
                                outputWriter.newLine();
                            }
                            outputWriter.flush();
                            outputWriter.close();

                            soos.writeObject("OK");
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Client disconnected.");
        }
    }
}
