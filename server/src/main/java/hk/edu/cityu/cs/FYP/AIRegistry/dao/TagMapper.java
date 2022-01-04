package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

@Mapper
public interface TagMapper {

    @Results({
        @Result(property = "projectId", column = "projectId"),
        @Result(property = "tag",column = "tag"),
        @Result(property = "tagTC",column = "tag_TC"),
        @Result(property = "tagSC",column = "tag_SC")
    })
    @Select("""
    SELECT * FROM `Tag` WHERE projectId = #{projectId}
    """)
    public List<Tag> getTagsByProjectId(int projectId);

    @Insert("""
        INSERT INTO `Tag` (`projectId`,`tag`,`tag_TC`,`tag_SC`)
        VALUES 
        (#{projectId},#{tag},#{tagTC},#{tagSC})
    """)
    public void addTag(Tag t);

    @Delete("""
    DELETE FROM `Tag` WHERE projectId = #{projectId} AND `tag` = #{tag}
    """)
    public void deleteTag (Tag t);

    @Select("""
    SELECT `projectId` from Tag
    WHERE tag = #{query} or 
    tag_TC = #{query} or
    tag_SC = #{query}
    """)
    public List<Integer> searchByTag(String query);

}
