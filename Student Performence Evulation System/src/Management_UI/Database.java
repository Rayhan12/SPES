package Management_UI;



import DataBase.Course_PLO;
import DataBase.Course_PLO_Mark;
import DataBase.PLO;
import DataBase.PLO;
import DataBase.PLO_Gained_From_Course;
import DataBase.SeePLOAA;
import DataBase.SemYear;
import DataBase.Sem_PLO;
import DataBase.Temp_student;
import DataBase.V_table_sub;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;





public class Database {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private CallableStatement cs;
    
    
    public Database()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spes","root","issb127979");
            st = (Statement) con.createStatement();
            if(con!=null)
            {
                System.out.println("Connected!!");
            }
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error found");
        }
    }
    
    ///User LOGIN-----------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    ///User LOGIN-----------------------------------------------------------------------------------------------------
    
    ArrayList<PLO> getAveragePLO = new ArrayList<PLO>();
    ArrayList<Sem_PLO> SemesterWisePLOAverage = new ArrayList<Sem_PLO>();
    ArrayList<Sem_PLO> SemesterWisePLOAverageAchieved = new ArrayList<Sem_PLO>();
    ArrayList<Course_PLO> CourseWiseAchievablePLO = new ArrayList<Course_PLO>();
    ArrayList<Course_PLO> CourseWiseAchievedPLO = new ArrayList<Course_PLO>();
    ArrayList<String> PLOArray = new ArrayList<String>();
    ArrayList<String> COArray = new ArrayList<String>();
    ArrayList<String> CourseArray = new ArrayList<String>();
    ArrayList<SemYear> GetYear = new ArrayList<SemYear>();

    public ArrayList<PLO> getGetAveragePLO() {
        return getAveragePLO;
    }

    public ArrayList<Sem_PLO> getSemesterWisePLOAverage() {
        return SemesterWisePLOAverage;
    }

    public ArrayList<Sem_PLO> getSemesterWisePLOAverageAchieved() {
        return SemesterWisePLOAverageAchieved;
    }

    public ArrayList<String> getCourseArray() {
        return CourseArray;
    }

    public ArrayList<Course_PLO> getCourseWiseAchievablePLO() {
        return CourseWiseAchievablePLO;
    }

    public ArrayList<Course_PLO> getCourseWiseAchievedPLO() {
        return CourseWiseAchievedPLO;
    }

   	

    
    
    
    
    //==================================================================================================================
    //Small Querys
    
    
    
    
    public Integer Get_Average_PLO_Achieved() throws SQLException
    {
        String sql = "select count(pl_id) AS count from(select plo.pl_id as pl_id,(((Sum(Answer.omark*100/Answer.fmark)*100)/plo.fmark)/10) As ploperc from reg inner join Answer on Answer.r_id = reg.r_id inner join Question On  Question.q_id = Answer.q_id inner join Co on CO.co_id = Question.co_id inner join plo on plo.pl_id = co.pl_id group by plo.pl_id having ploperc>40) as dertab;";
        rs = st.executeQuery(sql);
        Integer count = null;
        while(rs.next())
        {
              count=rs.getInt("count");
        }   
        return count;
        
    }
    
        public void GetAllYear() throws SQLException
    {
        String sql = "SELECT DISTINCT year , sem FROM SECTION";
        rs = st.executeQuery(sql); 
        while(rs.next())
        {
              GetYear.add(new SemYear(rs.getInt("year"),rs.getString("sem")));
        }   
        
    }
    
        public void GetAllPloinList() throws SQLException
    {
        String sql = "SELECT pl_id AS 'plo_id' FROM plo ORDER BY pl_no";
        rs = st.executeQuery(sql); 
        while(rs.next())
        {
              PLOArray.add(rs.getString("plo_id"));
        }   
        
    }
        
        public void GetAllCOinList() throws SQLException
    {
        String sql = "SELECT co_id AS 'co_id' FROM co";
        rs = st.executeQuery(sql); 
        while(rs.next())
        {
              COArray.add(rs.getString("co_id"));
        }   
        
    }
        
        public void GetAllCourseinList() throws SQLException
    {
        String sql = "SELECT c_id AS 'Course' FROM Course";
        rs = st.executeQuery(sql); 
        while(rs.next())
        {
              CourseArray.add(rs.getString("Course"));
        }   
        
    }
    
    public Integer GetTotalNumberOfStudents() throws SQLException
    {
        String sql = "SELECT count(*) AS 'Number' FROM STUDENT";
        rs = st.executeQuery(sql); Integer number = null;
        while(rs.next())
        {
           number = rs.getInt("Number");     
        }   
        return number;
    }
    
        public Integer GetTotalNumberOfFacultys() throws SQLException
    {
        String sql = "SELECT count(*) AS 'Number' FROM Faculty";
        rs = st.executeQuery(sql); Integer number = null;
        while(rs.next())
        {
           number = rs.getInt("Number");     
        }   
        return number;
    }      
    public Integer GetTotalNumberOfCourses() throws SQLException
    {
        String sql = "SELECT count(*) AS 'Number' FROM Course";
        rs = st.executeQuery(sql); Integer number = null;
        while(rs.next())
        {
           number = rs.getInt("Number");     
        }   
        return number;
    }
    
    public Integer GetTotalNumberOfPLOs() throws SQLException
    {
        String sql = "SELECT count(*) AS 'Number' FROM PLO";
        rs = st.executeQuery(sql); Integer number = null;
        while(rs.next())
        {
           number = rs.getInt("Number");     
        }   
        return number;
    }
    
    public Float GetAvgPLOLable() throws SQLException
    {
        int numOfs = GetTotalNumberOfStudents();
        cs = con.prepareCall("{call get_avg_plo_all_student(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, numOfs);
        rs = cs.executeQuery();
        Float number = null;
        while(rs.next())
        {
           number = rs.getFloat("Number");     
        }   
        return number;
    }
    
    
    public Float GetPLOmapingAVG() throws SQLException
    {
        String sql = "Select round(sum(PLO_number)/Count(course_number),2) AS 'maping' From (Select co.c_id AS 'course_number' , count(pl_id) As 'PLO_number' from Course inner join Co on co.c_id = course.c_id group by co.c_id) As math_table;";
        rs = st.executeQuery(sql); Float number = null;
        while(rs.next())
        {
           number = rs.getFloat("maping");     
        }   
        return number;
    }

    //==================================================================================================================
    
    public void getStudent() throws SQLException
    {
        String sql = "SELECT * FROM STUDENT;";
        rs = st.executeQuery(sql);
        while(rs.next())
        {
            int id      = rs.getInt("student_ID");
            String name = rs.getString("name");
            float  cgpa = rs.getFloat("cgpa");
            int semester = rs.getInt("semester");
            System.out.println("ID: "+id+" Name: "+name+" CGPA: "+cgpa+" Semester: "+semester );
            //System.out.println("ID: "+id+" Name: "+name);
        }
        
    }
    
    public void setStudent(int id , String name , float cgpa , int semester) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "INSERT INTO STUDENT VALUES (?,?,?,?)";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2,name);
        ps.setFloat(3, cgpa);
        ps.setInt(4, semester);
        ps.executeUpdate();
        
    }
    
        public String check()
    {
        if(con !=null)
        {
            System.out.println("ok");
            return "ok";
        }
        else 
            
            System.out.println("Not Working");
        return "Not Working";
    }
        
        
     public void setdata(String xcol , int ycol ) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "INSERT INTO DATA VALUES (?,?)";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, xcol);
        ps.setInt(2,ycol);
        ps.executeUpdate();
        
    }
     
     public void GetAveragePLO() throws SQLException
     {
         String sql = "select plo.pl_id AS ID , plo.fmark As 'Full Mark' ,  (((Sum(Answer.omark*100/Answer.fmark)*100)/plo.fmark)/10) As 'PLO' from reg inner join Answer on Answer.r_id = reg.r_id inner join Question On  Question.q_id = Answer.q_id inner join Co on CO.co_id = Question.co_id inner join plo on plo.pl_id = co.pl_id group by plo.pl_id order by plo.pl_no asc;";
         rs = st.executeQuery(sql);
         
         while(rs.next())
         {
             getAveragePLO.add(new PLO(rs.getString("ID"), rs.getFloat("PLO")));
         }
     }
     
    public void GetSemesterWiseAvgPloCount( String plo_id) throws SQLException
    {
        
        cs = con.prepareCall("{call Semester_wise_PLO(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, plo_id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterWisePLOAverage.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("PLO_Count")));
        }   

    }
    
    
    public void GetSemesterWiseAvgAchievedPloCount( String plo_id) throws SQLException
    {
        
        cs = con.prepareCall("{call Semester_wise_plo_achived(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, plo_id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterWisePLOAverageAchieved.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("PLO_Count")));
        }   

    }
    
    public void GetExpectedPLObyCourse( String Course_id) throws SQLException
    {
        
        cs = con.prepareCall("{call Average_plo_by_course(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, Course_id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                CourseWiseAchievablePLO.add(new Course_PLO(rs.getString("PLO_ID"), rs.getFloat("Achievable")));
        }   

    }
    
    public void GetAchivedPLObyCourse( String Course_id) throws SQLException
    {
        
        cs = con.prepareCall("{call Average_achieved_plo_by_course(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, Course_id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                CourseWiseAchievedPLO.add(new Course_PLO(rs.getString("PLO_count"), rs.getFloat("Achieved")));
        }   

    }
    //Setting Student Evulation =====================================================================================================
    //Setting Student Evulation *****************************************************************************************************
    //Setting Student Evulation *****************************************************************************************************
    //Setting Student Evulation =====================================================================================================
    
    
    
    ArrayList<Temp_student> Basic_Student = new ArrayList<Temp_student>();

    public ArrayList<Temp_student> getBasic_Student() {
        return Basic_Student;
    }
    
    
    
    public void GetAllStudents() throws SQLException
     {
         String sql = "Select * from Student";
         rs = st.executeQuery(sql);
         
         while(rs.next())
         {
             Basic_Student.add(new Temp_student(rs.getInt("st_id"),rs.getString("Name"),rs.getString("gender"),rs.getDate("dob").toLocalDate(),rs.getString("email")));
         }
     }
    
    
    public Integer GetstudentSemester( Integer id) throws SQLException
    {
        cs = con.prepareCall("{call Get_Semester(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        Integer sem = null;
        while(rs.next())
        {
                sem = rs.getInt("semester");
        }   
        return sem;
    }
    
    
    public Float GetstudentAveragePLO(Integer id) throws SQLException
    {
        cs = con.prepareCall("{call Average_plo_by_student(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        Float avg = null;
        while(rs.next())
        {
                avg = rs.getFloat("Average");
        }   
        return avg;
    }
    
        public PLO GetstudentMAXPLO( Integer id) throws SQLException
    {
        PLO Student_MaxPLO = new PLO();
        cs = con.prepareCall("{call Max_plo_by_student(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                Student_MaxPLO.name = rs.getString("PLO_id");
                Student_MaxPLO.point = rs.getFloat("Achived");
        }   
        return Student_MaxPLO;
    }
        
        public PLO GetstudentMINPLO( Integer id) throws SQLException
    {
        PLO Student_MinPLO = new PLO();
        cs = con.prepareCall("{call Min_plo_by_student(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                Student_MinPLO.name = rs.getString("PLO_id");
                Student_MinPLO.point = rs.getFloat("Achived");
        }   
        return Student_MinPLO;
    }
     
     //DataPassing -----------------------------------------------------------------------------DataPassing
     //DataPassing -----------------------------------------------------------------------------DataPassing
     //DataPassing -----------------------------------------------------------------------------DataPassing
        
        
        public Integer getStudentID() throws SQLException
    {
        String sql = "SELECT st_id AS id FROM DataPassing";
        rs = st.executeQuery(sql);
        Integer id = null;
        while(rs.next())
        {
            id  = rs.getInt("id");
        }
        return id;
        
    }
        
         public String getCourseID() throws SQLException
    {
        String sql = "SELECT c_id AS courseID FROM CoursePassing";
        rs = st.executeQuery(sql);
        String id = null;
        while(rs.next())
        {
            id  = rs.getString("courseID");
        }
        return id;
        
    }
    
    public void setStudentID(int id ) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "INSERT INTO DataPassing (st_id) VALUES (?)";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    
        public void setCourseID(String courseID ) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "INSERT INTO CoursePassing (c_id) VALUES (?)";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, courseID);
        ps.executeUpdate();
    }
    
    public void DeleteStudentID(int id ) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "Delete from Datapassing where st_id = ?;";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public void DeleteCourseID(String courseID) throws SQLException
   // public void setStudent(int id , String name ) throws SQLException
    {
        String sql = "Delete from Datapassing where st_id = ?;";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, courseID);
        ps.executeUpdate();
    }
    
    
    // Student Profile Data Passing------------------------------------------------------------------------------------------
    // Student Profile Data Passing------------------------------------------------------------------------------------------
    // Student Profile Data Passing------------------------------------------------------------------------------------------
    // Student Profile Data Passing------------------------------------------------------------------------------------------
    // Student Profile Data Passing------------------------------------------------------------------------------------------
    ArrayList<PLO> getAverageVSAchievedPLOByStudent = new ArrayList<PLO>();
    ArrayList<Sem_PLO> StudentwisePLOAttempt = new ArrayList<Sem_PLO>();
    ArrayList<Sem_PLO> StudentwisePLOAchieved = new ArrayList<Sem_PLO>();
    ArrayList<SeePLOAA> SemesterwisePLOAchievedbyStudent = new ArrayList<SeePLOAA>();
    ArrayList<SeePLOAA> SemesterwisePLOAchievablebyStudent = new ArrayList<SeePLOAA>();
    ArrayList<PLO_Gained_From_Course> PLOGained_from_Cources = new ArrayList<PLO_Gained_From_Course>();

    public ArrayList<PLO> getAverageVSAchievedPLOByStudent() {
        return getAverageVSAchievedPLOByStudent;
    }


    public void GetAverageVSAchievedPLOByStudent( Integer id) throws SQLException
    {
        
        cs = con.prepareCall("{call Achieved_plo_by_student(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                getAverageVSAchievedPLOByStudent.add(new PLO(rs.getString("PLO_id"), rs.getFloat("Achived")));
        }   

    }
    
    public void GetStudentwisePloAttemptCount( Integer id) throws SQLException
    {
        
        cs = con.prepareCall("{call Student_Semester_wise_PLO_attempt(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                StudentwisePLOAttempt.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("attempts")));
        }   

    }
    
    
    public void GetStudentwisePloAchievedCount( Integer id) throws SQLException
    {
        
        cs = con.prepareCall("{call Student_Semester_wise_PLO_achived(?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next())
        {
                StudentwisePLOAchieved.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("Achieved")));
        }   

    }
    
    public void GetSemesterwisePloAchiveableByStudent( Integer id , String semester , Integer Tyear ) throws SQLException
    {
        
        cs = con.prepareCall("{call Semester_wise_PLO_achived_and_achivable_by_student(?,?,?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.INTEGER);
        cs.setInt(1, id);
        cs.setString(2, semester);
        cs.setInt(3, Tyear);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterwisePLOAchievablebyStudent.add(new SeePLOAA(rs.getString("plos"), rs.getFloat("achievable"), rs.getFloat("achieved")));
        }   

    }
    
    
    public void GetSemesterwisePloAchievedByStudent( Integer id , String semester , Integer Tyear ) throws SQLException
    {
        
        cs = con.prepareCall("{call Semester_wise_PLO_achived_and_achivable_by_student(?,?,?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.INTEGER);
        cs.setInt(1, id);
        cs.setString(2, semester);
        cs.setInt(3, Tyear);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterwisePLOAchievedbyStudent.add(new SeePLOAA(rs.getString("plos"), rs.getFloat("achievable"), rs.getFloat("achieved")));
        }   

    }
    
    public void GetPloGainedFromCourse( Integer id , String selectedplo) throws SQLException
    {
        
        cs = con.prepareCall("{call PLO_Gained_From_Course(?,?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.setInt(1, id);
        cs.setString(2, selectedplo);
        rs = cs.executeQuery();
        while(rs.next())
        {
                PLOGained_from_Cources.add(new PLO_Gained_From_Course(rs.getString("course"), rs.getFloat("cper"), rs.getFloat("pper")));
        }   

    }
    
    //Hard One------------------
    ArrayList<Course_PLO_Mark> markList = new ArrayList<Course_PLO_Mark>();
    
    

    
    
    public void Get_A_Single_CourseWisePloMark( Integer id , String courseid) throws SQLException
    {
        
        cs = con.prepareCall("{call Course_wise_plo_mark_For_table(?,?)} ");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.setInt(1, id);
        cs.setString(2, courseid);
        rs = cs.executeQuery();
        while(rs.next())
        {
                markList.add(new Course_PLO_Mark(rs.getString("course"), rs.getString("PLOID"), rs.getFloat("mark")));
        }   

    }
    
    
    
    //Course setup start-------------------------------------------------------------------------------------------------- 
     //Course setup start-------------------------------------------------------------------------------------------------- 
        //Course setup start-------------------------------------------------------------------------------------------------- 
        //Course setup start-------------------------------------------------------------------------------------------------- 
    ArrayList<PLO> Course_PLO_Contribution = new ArrayList<PLO>();
    
        public Integer Get_Course_Wise_PLO_Count( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Course_wise_plo_count(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        Integer x = null;
        while(rs.next())
        {
                x=rs.getInt("count");
        }   
        return x;
    }
        
    
        ArrayList<PLO> PLO_For_Course = new ArrayList<PLO>();
        public void Get_Course_PLO_Contribution_BY_plo( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Course_PLO_Contribution_BY_plo(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                PLO_For_Course.add(new PLO(rs.getString("plos").toUpperCase(),rs.getFloat("mark")));
        }   
       
    }
        
        
        
        
        public Float Get_Course_Average_PLO_Contribution( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Course_average_plo_contribution(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        Float x = null;
        while(rs.next())
        {
                x=rs.getFloat("Average");
        }   
        return x;
    }
        
        public Integer Get_Course_Section_offered( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Count_course_section_offerd(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        Integer x = null;
        while(rs.next())
        {
                x=rs.getInt("cou");
        }   
        return x;
    }    
        
     public void Get_Course_PLO_Contribution( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Course_Wise_PLO_Contribution(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                Course_PLO_Contribution.add(new PLO(rs.getString("plos"),rs.getFloat("final_marks")));
        }   
        
    }    
     
     
     public void Get_Course_Semester_Wise_Avg_Plo_Count( String plo_id , String courseID) throws SQLException
    {
        
        cs = con.prepareCall("{call Course_Semester_wise_PLO_attempt(?,?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.setString(1, plo_id);
        cs.setString(2, courseID);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterWisePLOAverage.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("PLO_Count")));
        }   

    }
    
    
    public void Get_Course_Semester_Wise_Avg_Achieved_Plo_Count( String plo_id , String courseID) throws SQLException
    {
        
        cs = con.prepareCall("{call Course_Semester_wise_PLO_achieved(?,?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.setString(1, plo_id);
        cs.setString(2, courseID);
        rs = cs.executeQuery();
        while(rs.next())
        {
                SemesterWisePLOAverageAchieved.add(new Sem_PLO(rs.getString("sem"), rs.getInt("year"), rs.getInt("PLO_Count")));
        }   

    }
    
    ArrayList<String> Related_PLO_List = new ArrayList<String>();
    
    public void Get_Course_Related_PLO_List( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call Course_related_PLO_LIST(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                Related_PLO_List.add(rs.getString("plos"));
        }   
        
    }
    
    
    
    //Verdit table implementation----------------------------------------------------------------------
     ArrayList<V_table_sub> partone = new ArrayList<V_table_sub>();
     ArrayList<V_table_sub> parttwo = new ArrayList<V_table_sub>();
    
    
     public Integer Get_V_table_total_students( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call V_table_total_students(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        Integer x = null;
        while(rs.next())
        {
                x=rs.getInt("total");
        }   
        return x;
    }
     
      public void Get_V_table_sub_data_one( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call V_table_part_one(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                partone.add(new V_table_sub(rs.getString("nms").toUpperCase(),rs.getInt("snum")));
        }   
        
    }
     
      public void Get_V_table_sub_data_two( String courseid) throws SQLException
    {      
        cs = con.prepareCall("{call V_table_part_two(?)} ");
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.setString(1, courseid);
        rs = cs.executeQuery();
        
        while(rs.next())
        {
                parttwo.add(new V_table_sub(rs.getString("co"),rs.getInt("snum")));
        }   
        
    }
      
      // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
       // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
       // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
       // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
       // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
       // IMPLEMENTING PROGRAM DESIGN-------------------------------------------------------------------------------
      
      
      public void ADD_Course(String CourseID , String CourseName ) throws SQLException
    {
        String sql = "INSERT INTO Course(c_id,name,p_id) VALUES (?,?,?);";
        String p_id = "B.Sc.CSE";

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, CourseID);
        ps.setString(2,CourseName);
        ps.setString(3, p_id);
        ps.executeUpdate();
        
    }
      
     public void ADD_CO(String COID ,String CourseID , String PLOID ) throws SQLException
    {
        String sql = "INSERT INTO CO(co_id,oMark,fMark,c_id,pl_id) VALUES (?,?,?,?,?);";
        Integer oMark = 0 , fMark = 100;

        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, COID);
        ps.setInt(2, oMark);
        ps.setInt(3,fMark);
        ps.setString(4, CourseID);
        ps.setString(5, PLOID);
        ps.executeUpdate();
        UPDATE_PLO_FULLMARK(PLOID); // Updating PLO Full mark
    }
     
     public void UPDATE_PLO_FULLMARK(String PLOID ) throws SQLException
    {
        String sql = "UPDATE PLO SET fMARK = fMARK+100 WHERE pl_id = ?;"; 
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, PLOID);
        ps.executeUpdate();
        
    }
      
}
