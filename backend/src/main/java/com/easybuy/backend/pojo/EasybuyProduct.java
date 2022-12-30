package com.easybuy.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasybuyProduct {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private Integer categorylevel1id;
    private Integer categorylevel2id;
    private Integer categorylevel3id;
    private String filename;
    private Integer isdelete;


}
