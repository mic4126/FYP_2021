package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;

@Mapper
public interface DetailMapper {

    @Results({
            @Result(property = "detailName", column = "detailName"),
            @Result(property = "detailDesc", column = "detailDesc"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "detailId", column = "detailId")
    })
    @Select("""
            SELECT `projectId`, `detailId`, `detailName`, `detailDesc`
            FROM Detail
            WHERE `projectId` = #{projectId}
            AND `deleted` IS null
            """)
    public List<Detail> getDetailsByProjectId(int projectId);

    @Results({
            @Result(property = "detailName", column = "detailName_TC"),
            @Result(property = "detailDesc", column = "detailDesc_TC"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "detailId", column = "detailId")
    })
    @Select("""
            SELECT `projectId`, `detailId`, `detailName_TC`, `detailDesc_TC`
            FROM Detail
            WHERE `projectId` = #{projectId}
            AND `deleted` IS null
            """)
    public List<Detail> getDetailsByProjectIdTC(int projectId);

    @Results({
            @Result(property = "detailName", column = "detailName_SC"),
            @Result(property = "detailDesc", column = "detailDesc_SC"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "detailId", column = "detailId")
    })
    @Select("""
            SELECT `projectId`, `detailId`, `detailName_SC`, `detailDesc_SC`
            FROM Detail
            WHERE `projectId` = #{projectId}
            AND `deleted` IS null
            """)
    public List<Detail> getDetailsByProjectIdSC(int projectId);

    @Insert("""
            INSERT INTO `Detail` (`projectId`,`detailName`)
            VALUES
            (#{projectId},#{detailName})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "detailId")
    public int addDetail(Detail detail);

    @Update("""
            UPDATE `detail` SET
            `detailName` = #{detailName},
            `detailDesc` = #{detailDesc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetail(Detail detail);

    @Update("""
            UPDATE `detail` SET
            `detailName_TC` = #{detailName},
            `detailDesc_TC` = #{detailDesc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailTC(Detail detail);

    @Update("""
            UPDATE `detail` SET
            `detailName_SC` = #{detailName},
            `detailDesc_SC` = #{detailDesc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailSC(Detail detail);

    @Update("""
            UPDATE `detail` SET
            `detailName` = #{detailName}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailName(int detailId, String detailName);

    @Update("""
            UPDATE `detail` SET
            `detailName_TC` = #{detailName}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailNameTC(int detailId, String detailName);

    @Update("""
            UPDATE `detail` SET
            `detailName_SC` = #{detailName}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailNameSC(int detailId, String detailName);

    @Update("""
            UPDATE `detail` SET
            `detailDesc` = #{desc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailDesc(int detailId, String desc);

    @Update("""
            UPDATE `detail` SET
            `detailDesc_TC` = #{desc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailDescTC(int detailId, String desc);

    @Update("""
            UPDATE `detail` SET
            `detailDesc_SC` = #{desc}
            WHERE `detailId` = #{detailId}
            """)
    public void setDetailDescSC(int detailId, String desc);

    @Update("""
            UPDATE `detail` SET
            `deleted` = Now()
            WHERE `detailId` = #{detailId}
            """)
    public void deleteDetail(int detailId);
}
