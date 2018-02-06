package com.wf2311.jfeng.file;

import com.wf2311.jfeng.exception.CustomException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 解压缩文件
 *
 * @author wf2311
 */
public class ZipUtils {
    private static final String ENCODING = "GBK";

    public static void zip(String inputFilePath, String zipFileName) throws IOException {
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            throw new IOException("原始文件不存在!!!");
        }
        File baseTarZipFile = new File(zipFileName).getParentFile();
        if (!baseTarZipFile.exists() && !baseTarZipFile.mkdirs()) {
            throw new IOException("目标文件无法创建!!!");
        }
        try (FileOutputStream out = new FileOutputStream(new String(zipFileName.getBytes(ENCODING)));
             BufferedOutputStream bos = new BufferedOutputStream(out);
             ZipOutputStream zOut = new ZipOutputStream(bos)) {
            zip(zOut, inputFile, inputFile.getName());
        }
    }

    private static void zip(ZipOutputStream zOut, File file, String base) throws IOException {
        // 如果文件句柄是目录
        if (file.isDirectory()) {
            // 获取目录下的文件
            File[] listFiles = file.listFiles();
            // 建立ZIP条目
            zOut.putNextEntry(new ZipEntry(base + "/"));
            base = (base.length() == 0 ? "" : base + "/");
            if (listFiles != null && listFiles.length > 0) {
                // 遍历目录下文件
                for (File f : listFiles) {
                    // 递归进入本方法
                    zip(zOut, f, base + f.getName());
                }
            }
        }
        // 如果文件句柄是文件
        else {
            if (Objects.equals(base, "")) {
                base = file.getName();
            }
            // 填入文件句柄
            zOut.putNextEntry(new ZipEntry(base));
            // 开始压缩
            // 从文件入流读,写入ZIP 出流
            writeFile(zOut, file);
        }
    }

    private static void writeFile(ZipOutputStream zOut, File file)
            throws IOException {
        try (FileInputStream in = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(in);) {
            int len = 0;
            byte[] buff = new byte[2048];
            while ((len = bis.read(buff)) != -1) {
                zOut.write(buff, 0, len);
            }
            zOut.flush();
        }

    }

    /****
     * 解压
     *
     * @param zipPath         zip文件路径
     * @param destinationPath 解压的目的地点
     * @param encode           文件名的编码字符集
     */
    public static void unzip(String zipPath, String destinationPath, String encode) {
        File zipFile = new File(zipPath);
        if (!zipFile.exists()) {
            throw new CustomException("zip file " + zipPath
                    + " does not exist.");
        }
        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
        expand.setSrc(zipFile);
        expand.setDest(new File(destinationPath));
        expand.setEncoding(encode);
        expand.execute();
    }

    public static void unzip(String zipPath, String destinationPath) {
        unzip(zipPath, destinationPath, ENCODING);
    }
}
