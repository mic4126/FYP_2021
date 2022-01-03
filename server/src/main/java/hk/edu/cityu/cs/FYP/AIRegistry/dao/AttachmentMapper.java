package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

@Mapper
public interface AttachmentMapper {

    @Select("""
            SELECT attachmentID from attachment
            WHERE projectId = `#{projectId}`
            AND detailId IS NULL
            """)
    public List<Integer> getProjectAttachments(int projectId);

    @Select("""
            SELECT attachmentID from attachment
            WHERE detailId = #{detailId}
            """)
    public List<Integer> getDetailAttachments(int detailId);

    @Results({
            @Result(property = "filenName", column = "fileName"),
            @Result(property = "origFileName", column = "orig_filename"),
            @Result(property = "origExt", column = "orig_ext")
    })
    @Select("""
            SELECT filename, orig_filename, orig_ext FROM attachment
            WHERE attachmentID = #{attachmentId}
            """)
    public AttachmentDownload getAttachment(int attachmentId);

    @Update("""
            UPDATE ATTACHMENT SET
            deleted = NOW()
            WHERE attachmentID = #{attachmentId}
            """)
    public void deleteAttachment(int attachmentId);

    @Insert("""
            INSERT INTO `attachment`
            (`filename`,`orig_filename`,`orig_ext`,`projectId`)
            VALUES
            (#{fileName},#{origFileName},#{origExt},#{projectId})
                """)
    @Options(useGeneratedKeys = true, keyProperty = "attachmentId", keyColumn = "attachmentID")
    public void addProjectAttachment(AttachmentUpload attachmentUpload);

    @Insert("""
            INSERT INTO `attachment`
            (`filename`,`orig_filename`,`orig_ext`,`projectId`)
            VALUES
            (#{fileName},#{origFileName},#{origExt},#{detailId})
                """)
    @Options(useGeneratedKeys = true, keyProperty = "attachmentId", keyColumn = "attachmentID")
    public void addDetailAttachment(AttachmentUpload attachmentUpload);
}
