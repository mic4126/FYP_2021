package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Mapper
public interface ProjectUserMapper {
    @Insert("""
            <script>
            INSERT INTO Project_User (projectID,username) VALUES
            <foreach item='username' collection='developers' open='' separator=',' close=''>
                (#{projectId}, #{username})
            </foreach>
            </script>
            """)
    public void addDevelopers(@Param("projectId") int projectId,
            @Param("developers") List<String> developers);

    @Insert("INSERT INTO Project_User (projectID,username) VALUES (#{projectId},#{username})")
    public void addDeveloper(@Param("projectId") int projectId, @Param("username") String username);

    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "userType", column = "userType"),
            @Result(property = "hashedPassword", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "newPassword", column = "new_password"),
            @Result(property = "newSalt", column = "new_salt")
    })
    @Select("""
            SELECT username, email, firstName, lastName, userType,
                        password, salt, new_password, new_salt
                    FROM user
                    WHERE username IN
                    (Select username FROM Project_User
                    WHERE projectId = #{projectId})
            """)
    public List<UserInfo> getDevelopersByProjectId(int projectId);

    @Delete("""
                DELETE FROM Project_User where projectId = #{projectId} AND username = #{username}
            """)

    public void deleteDeveloper(int projectId, String username);

}
