package com.fastcompus.ch3.di3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("setting.properties") // 해당 파일을 읽어와 존재하는 값들을 사용
public class SysInfo {
    @Value("#{systemProperties['user.timezone']}") // 시간대를 알 수 있다.
    String timeZone;

    @Value("#{systemEnvironment['PWD']}") // 현재 작업 디렉토리
    String currDir;

    @Value("${autosaveDir}")
    String autosaveDir;

    @Value("${autosaveInterval}")
    int autosaveInterval;

    @Value("${autosave}")
    boolean autosave;

    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", currDir='" + currDir + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                ", autosave=" + autosave +
                '}';
    }
}
