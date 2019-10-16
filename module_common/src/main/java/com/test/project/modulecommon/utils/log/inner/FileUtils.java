package com.test.project.modulecommon.utils.log.inner;

import java.io.File;
import java.io.IOException;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/16 16:27
 * Modify by
 * Modification date：
 * Modify content：
 */
public class FileUtils {

    private static final String TAG = "common_FileUtils";


    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 创建文件夹<br/>
     * <p>
     * 如果存在,则返回; 如果不存在则创建
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 创建失败
     */
    private static boolean createDirOrExists(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    /**
     * 创建文件
     * <p>
     * 如果文件存在,则返回; 不存在,就创建
     *
     * @param file 需要创建的文件
     * @return {@code true}: 存在或创建成功<br>
     * {@code false}: 创建文件失败
     */
    static boolean createFileOrExists(File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile();
        if (!createDirOrExists(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LogInner.w(TAG, e, "创建文件失败");
            return false;
        }
    }

}
