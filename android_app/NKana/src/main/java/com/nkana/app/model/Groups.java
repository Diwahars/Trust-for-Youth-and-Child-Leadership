package com.nkana.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chokkar on 18-11-15.
 */
public class Groups {

    private String name;
    private int max_speed;
    private int min_battery;
    private boolean speed_alert;
    private boolean collision_alert;
    private boolean min_battery_alert;
    private boolean fence_violation_alert;
    private boolean enabled;
    private boolean sync;

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }

    public int getMin_battery() {
        return min_battery;
    }

    public void setMin_battery(int min_battery) {
        this.min_battery = min_battery;
    }

    public boolean isSpeed_alert() {
        return speed_alert;
    }

    public void setSpeed_alert(boolean speed_alert) {
        this.speed_alert = speed_alert;
    }

    public boolean isCollision_alert() {
        return collision_alert;
    }

    public void setCollision_alert(boolean collision_alert) {
        this.collision_alert = collision_alert;
    }

    public boolean isMin_battery_alert() {
        return min_battery_alert;
    }

    public void setMin_battery_alert(boolean min_battery_alert) {
        this.min_battery_alert = min_battery_alert;
    }

    public boolean isFence_violation_alert() {
        return fence_violation_alert;
    }

    public void setFence_violation_alert(boolean fence_violation_alert) {
        this.fence_violation_alert = fence_violation_alert;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("max_speed", max_speed);
            jsonObject.put("min_battery", min_battery);
            jsonObject.put("speed_alert", speed_alert);
            jsonObject.put("collision_alert", collision_alert);
            jsonObject.put("min_battery_alert", min_battery_alert);
            jsonObject.put("fence_violation_alert", fence_violation_alert);
            jsonObject.put("enabled", enabled);
            jsonObject.put("sync", sync);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }


}
