package com.fubang.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangchanglin
 * @ClassName ExportDocDTO
 * @Description TODO
 **/
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportDocDTO {

    private  String gradeS;
    private  String gradeE;
    private  String studentName;
    private  String studentNumber;
    private  String studentSex;
    private  String major;
    private  String grade;
    private  String studentClass;
    private  String resultClass0;
    private  String resultClass1;
    private  String resultClass2;
    private  String resultClass3;
    private  String resultName0;
    private  String resultName1;
    private  String resultName2;
    private  String resultName3;
    private  String resultTime0;
    private  String resultTime1;
    private  String resultTime2;
    private  String resultTime3;
    private  String rank0;
    private  String rank1;
    private  String rank2;
    private  String rank3;
    private  String score0;
    private  String score1;
    private  String score2;
    private  String score3;
    private  String scoreAll;
}
