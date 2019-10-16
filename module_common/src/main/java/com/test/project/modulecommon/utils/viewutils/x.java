package com.test.project.modulecommon.utils.viewutils;


/**
 * Author    zhouchuan
 * Describe：任务控制中心
 * Data:      2019/10/16 11:35
 * Modify by
 * Modification date：
 * Modify content：
 */
public final class x {
    private x() {

    }

    public static ViewInjector view() {
        if (Ext.viewInjector == null) {
            ViewInjectorImpl.registerInstance();
        }
        return Ext.viewInjector;
    }

    public static class Ext {
        private static ViewInjector viewInjector;

        private Ext() {

        }

        public static void setViewInjector(ViewInjector viewInjector) {
            Ext.viewInjector = viewInjector;
        }
    }
}
