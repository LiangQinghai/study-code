package cn.liangqinghai.study.netty.utils;

import io.netty.util.internal.StringUtil;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
public final class PackageScanner {

    /**
     * 包扫描
     *
     * @param packagePath
     * @return
     */
    public static List<String> scan(String packagePath) {
        List<String> clazzList = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(packagePath)) {
            return clazzList;
        }

        try {
            URL resource = PackageScanner.class.getClassLoader().getResource(packagePath.replaceAll("\\.", "/"));

            assert resource != null;
            File file = new File(resource.getPath());

            cycleScan(clazzList, file, packagePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazzList;
    }

    /**
     * 递归扫描包
     *
     * @param storage
     * @param file
     * @param packagePath
     */
    private static void cycleScan(List<String> storage, File file, String packagePath) {

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File pack : files) {
            if (pack.isDirectory()) {
                cycleScan(storage, file, packagePath);
            } else {
                if (pack.getName().endsWith(".class")) {
                    storage.add(packagePath + "." + pack.getName().replace(".class", ""));
                }
            }
        }

    }

}
