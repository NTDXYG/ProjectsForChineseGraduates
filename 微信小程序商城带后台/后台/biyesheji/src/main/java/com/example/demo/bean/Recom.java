package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recom implements Comparable<Recom>{
    double result;
    Goods goods;

    @Override
    public int compareTo(Recom o) {
        Double d1 =  o.getResult();
        Double d2 =  this.getResult();
        return d1.compareTo(d2);
    }
}
