package com.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2021/1/19
 * @Time:11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Demo {
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年级
     */
    @NotBlank(message = "年级不能为空")
    @ExcelProperty("年级")
    private String grade;

    @ExcelProperty("邮箱")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}.(com|net|org|edu|mil|cn|cc)$", message = "邮箱地址有误")
    private String email;

}
