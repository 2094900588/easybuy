package com.easybuy.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasybuyUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String loginname;
    private String username;
    private String password;
    private Integer sex;
    private String identitycode;
    private String email;
    private String mobile;
    private Integer type;
}
