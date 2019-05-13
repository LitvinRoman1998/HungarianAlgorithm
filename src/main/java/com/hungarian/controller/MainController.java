package com.hungarian.controller;

import com.hungarian.model.HungarianAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    @PostMapping("/calculate")
    public Map<String, String> calculate(@RequestParam Map<String,String> allRequestParams){
        int rowCount=Integer.parseInt(allRequestParams.get("rowCount"));
        int columnCount=Integer.parseInt(allRequestParams.get("columnCount"));
        double[][] array=new double[rowCount][columnCount];
        String solveWay=allRequestParams.get("solveWay");
        for (int i=0; i<rowCount; i++){
            for (int j = 0; j < columnCount; j++) {
                array[i][j]=Double.parseDouble(allRequestParams.get("input"+(i+1)+(j+1)));
            }
        }
        int[][] assignment=HungarianAlgorithm.hgAlgorithm(array,solveWay);
        double sum = 0;
        StringBuilder winAssigm=new StringBuilder("");
        for (int i = 0; i < assignment.length; i++) {
            //<COMMENT> to avoid printing the elements that make up the assignment
            winAssigm.append("array("+(assignment[i][0] + 1)+","+(assignment[i][1] + 1)+") ="+array[assignment[i][0]][assignment[i][1]]+"; ");
            sum = sum + array[assignment[i][0]][assignment[i][1]];
        }
        Map<String, String> rezultMap=new HashMap<>();
        rezultMap.put("rezult", winAssigm.toString());
        rezultMap.put("sum", String.valueOf(sum));
        return rezultMap;
    }
}
