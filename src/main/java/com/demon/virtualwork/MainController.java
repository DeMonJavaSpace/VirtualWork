package com.demon.virtualwork;

import com.demon.virtualwork.util.TextUtils;
import com.demon.virtualwork.util.ThreadUtils;
import com.demon.virtualwork.util.TimeUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.InputEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author DeMon
 * Date 2021/5/27.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
public class MainController implements Initializable {
    private static final String TAG = "MainController";
    @FXML
    public TextField tfTime;
    @FXML
    public TextField tfCount;
    @FXML
    public Label tvMsg;
    @FXML
    public TextField tfContent;
    private StringBuilder msgSb;
    private int time = 60;
    private int count = 1000;

    private int sum = 0;
    private Timer timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ThreadUtils.runOnUiThread(() -> tfContent.requestFocus());
    }

    public void onStart(ActionEvent actionEvent) {
        initStart();
        String timeText = tfTime.getText();
        if (!TextUtils.isEmpty(timeText)) {
            time = Integer.parseInt(timeText);
        }
        String countText = tfCount.getText();
        if (!TextUtils.isEmpty(countText)) {
            count = Integer.parseInt(countText);
        }
        printMsg("Hello world!");
        printMsg("将在" + time + "分钟内执行" + count + "次");
        long endTime = System.currentTimeMillis() + (long) time * 60 * 1000;
        printMsg("预期结束时间=" + TimeUtils.format(endTime));
        int intervals = time * 60 * 1000 / count;
        printMsg("间隔时间ms=" + intervals);
        Random random = new Random();
        timer = new Timer();
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int number = random.nextInt(95) + 32;
                Point point = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove(point.x, point.y);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                robot.keyPress(number);
                robot.keyRelease(number);
                sum++;
                if (sum >= count) {
                    printMsg("执行结束=" + TimeUtils.getNowTimeStr() + ",执行次数=" + sum);
                    timer.cancel();
                }
            }
        };
        printMsg("将在3s后开始执行～");
        ThreadUtils.runOnUiThread(() -> tfContent.requestFocus());
        timer.schedule(task, 3000, intervals);
    }

    public void onEnd(ActionEvent actionEvent) {
        System.out.println("onEnd");
        if (timer != null) {
            printMsg("手动结束=" + TimeUtils.getNowTimeStr() + ",执行次数=" + sum);
            timer.cancel();
            timer = null;
        }
    }

    private void initStart() {
        printMsg("");
        sum = 0;
        if (timer != null) {
            printMsg("重新执行=" + TimeUtils.getNowTimeStr());
            timer.cancel();
            timer = null;
        }
    }

    private void printMsg(String msg) {
        if (TextUtils.isEmpty(msg) || msgSb == null) {
            msgSb = new StringBuilder();
        } else {
            msgSb.append(msg).append("\n");
        }
        ThreadUtils.runOnUiThread(() -> {
            tvMsg.setText(msgSb.toString());
        });
    }

}