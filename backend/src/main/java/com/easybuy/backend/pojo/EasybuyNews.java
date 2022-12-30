package com.easybuy.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasybuyNews {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String createtime;

}
