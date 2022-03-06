package com.barbershop.api.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Calendar {
    public static JSONArray generateFromStartToEnd(String start, String end){
        JSONArray turno = new JSONArray();
        String [] split = start.split(":");
        int startHours = Integer.parseInt(split[0]);
        int startMinutes = Integer.parseInt(split.length > 1?split[1]:"0");
        split = end.split(":");
        int endHours = Integer.parseInt(split[0]);
        int endMinutes = Integer.parseInt(split.length > 1?split[1]:"0");
        for(int i = startHours; i<=endHours; i++){
            for(int j = i == startHours?startMinutes:0;j<(i==endHours?endMinutes:60);j+=15)
                turno.put(new JSONObject().append("hours",i).append("minutes",j).append("string",new StringBuilder().append(String.format("%02d", i)).append(":").append(String.format("%02d", j)).toString()).append("reserved",false));
        }
        return turno;
    }

    public static JSONArray generateCalendarFromDBString(String schedule){
        JSONArray jr = new JSONArray();
        String [] turnos = schedule.split(";");
        String [] horas;
        for(String turno : turnos){
            horas = turno.split("-");
            List<Object> list = generateFromStartToEnd(horas[0], horas[1]).toList();
            list.addAll(jr.toList());
            jr = new JSONArray(list);
        }
        return jr;
    }
}
