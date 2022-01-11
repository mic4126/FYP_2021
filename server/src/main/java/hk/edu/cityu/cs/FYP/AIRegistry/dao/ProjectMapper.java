package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

@Mapper
public interface ProjectMapper {

        @Select("""
                            SELECT `desc` FROM project
                            WHERE projectId = #{projectId}
                        """)
        public String getDesc(int projectId);

        @Update("""
                            UPDATE project SET
                            `desc` = #{projectDesc}
                            WHERE
                            projectId = #{projectId}
                        """)
        public void setDesc(int projectId, String projectDesc);

        @Select("""
                            SELECT `desc_TC` FROM project
                            WHERE projectId = #{projectId}
                        """)
        public String getDescTC(int projectId);

        @Update("""
                            UPDATE project SET
                            `desc_TC` = #{projectDesc}
                            WHERE
                            projectId = #{projectId}
                        """)
        public void setDescTC(int projectId, String projectDesc);

        @Select("""
                            SELECT `desc_SC` FROM project
                            WHERE projectId = #{projectId}
                        """)
        public String getDescSC(int projectId);

        @Update("""
                            UPDATE project SET
                            `desc_SC` = #{projectDesc}
                            WHERE
                            projectId = #{projectId}
                        """)
        public void setDescSC(int projectId, String projectDesc);

        @Insert("""
                        INSERT INTO PROJECT (projectName) VALUE (#{projectName})
                        """)
        @Options(useGeneratedKeys = true, keyProperty = "projectId")
        public int addProject(Project project);

        @Update("""
                        UPDATE project SET
                        email = #{email},
                        phoneNumber = #{phoneNumber},
                        department = #{department}
                        WHERE
                        projectId = #{projectId}
                        """)
        public void updateContact(Contact contact);

        @Results({
                        @Result(property = "email", column = "email"),
                        @Result(property = "department", column = "department"),
                        @Result(property = "phoneNumber", column = "phoneNumber")
        })
        @Select("""
        SELECT projectId, email, department, phoneNumber from `project` WHERE projectId = #{projectId}
        """)
        public Contact getContact(int projectId);


        @Select("Select projectName from project where projectId = #{projectId}")
        public String getProjectName(int projectId);

        @Select("Select projectName_TC from project where projectId = #{projectId}")
        public String getProjectNameTC(int projectId);

        @Select("Select projectName_SC from project where projectId = #{projectId}")
        public String getProjectNameSC(int projectId);

        @Update("""
                        UPDATE `project` SET
                        `projectName` = #{projectName}
                        WHERE
                        `projectId` = #{projectId}
                        """)
        public void setProjectName(int projectId, String projectName);

        @Update("""
                        UPDATE `project` SET
                        `projectName_TC` = #{projectName}
                        WHERE
                        `projectId` = #{projectId}
                        """)
        public void setProjectNameTC(int projectId, String projectName);

        @Update("""
                        UPDATE `project` SET
                        `projectName_SC` = #{projectName}
                        WHERE
                        `projectId` = #{projectId}
                        """)
        public void setProjectNameSC(int projectId, String projectName);

        @Results({
                        @Result(property = "projectName", column = "projectName"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc")
        })
        @Select("""
                        SELECT `projectId`, `projectName`, `desc` FROM project
                        WHERE projectId = #{projectId}
                        """)
        public Project getProject(int projectId);

        @Results({
                        @Result(property = "projectName", column = "projectName_TC"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc_TC")
        })
        @Select("""
                        SELECT `projectId`, `projectName_TC`, `desc_TC` FROM project
                        WHERE projectId = #{projectId}
                        """)
        public Project getProjectTC(int projectId);

        @Results({
                        @Result(property = "projectName", column = "projectName_SC"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc_SC")
        })
        @Select("""
                        SELECT `projectId`, `projectName_SC`, `desc_SC` FROM project
                        WHERE projectId = #{projectId}
                        """)
        public Project getProjectSC(int projectId);

        @Select("""
                           SELECT DISTINCT Project.projectId FROM Project
                        LEFT JOIN Detail
                        ON Project.projectId = Detail.projectId
                        WHERE
                           AND (projectName LIKE Concat( '%' ,#{query} ,'%') OR
                           projectName_TC LIKE Concat( '%' ,#{query} ,'%') OR
                           projectName_SC LIKE Concat( '%' ,#{query} ,'%') OR
                           Project.desc LIKE Concat( '%' ,#{query} ,'%') OR
                           Project.desc_TC LIKE Concat( '%' ,#{query} ,'%') OR
                           Project.desc_SC LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailDesc LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailDesc_TC LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailDesc_SC LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailName LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailName_TC LIKE Concat( '%' ,#{query} ,'%') OR
                           Detail.detailName_SC LIKE Concat( '%' ,#{query} ,'%')
                           )
                           """)
        public List<Integer> searchProject(String query);

        @Results({
                        @Result(property = "projectName", column = "projectName"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc")
        })
        @Select("""
                        SELECT `projectId`, `projectName`, `desc` FROM project
                        WHERE
                        projectId in
                        <foreach item='item' index='index' collection='projectIds'
                            open='(' separator=',' close=')'>
                            #{item}
                        </foreach>
                            """)
        public List<Project> getProjectsByProjectIDs(List<Integer> projectIds);

        @Results({
                        @Result(property = "projectName", column = "projectName_TC"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc_TC")
        })
        @Select("""
                        SELECT `projectId`, `projectName_TC`, `desc_TC` FROM project
                        WHERE
                        projectId in
                        <foreach item='item' index='index' collection='projectIds'
                            open='(' separator=',' close=')'>
                            #{item}
                        </foreach>
                        """)
        public List<Project> getProjectsByProjectIDsTC(List<Integer> projectIds);

        @Results({
                        @Result(property = "projectName", column = "projectName_SC"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc_SC")
        })
        @Select("""
                        SELECT `projectId`, `projectName_SC`, `desc_SC` FROM project
                        WHERE
                        projectId in
                        <foreach item='item' index='index' collection='projectIds'
                            open='(' separator=',' close=')'>
                            #{item}
                        </foreach>
                        """)
        public List<Project> getProjectsByProjectIDsSC(List<Integer> projectIds);

        @Results({
                        @Result(property = "projectName", column = "projectName"),
                        @Result(property = "projectId", column = "projectId"),
                        @Result(property = "projectDesc", column = "desc")
        })
        @Select("""
                        SELECT `projectId`, `projectName`, `desc` FROM project
                        WHERE
                        deleted is null
                            """)
        public List<Project> getProjects();

}
