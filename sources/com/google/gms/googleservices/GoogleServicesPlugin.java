package com.google.gms.googleservices;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.typehandling.ShortTypeHandling;
import org.codehaus.groovy.transform.ImmutableASTTransformation;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/* compiled from: GoogleServicesPlugin.groovy */
public class GoogleServicesPlugin implements Plugin<Project>, GroovyObject {
    private static /* synthetic */ SoftReference $callSiteArray = null;
    private static /* synthetic */ ClassInfo $staticClassInfo = null;
    private static /* synthetic */ ClassInfo $staticClassInfo$ = null;
    public static final String JSON_FILE_NAME = "google-services.json";
    public static final String MINIMUM_VERSION = "9.0.0";
    public static final String MODULE_CORE = "firebase-core";
    public static final String MODULE_GROUP = "com.google.android.gms";
    public static final String MODULE_GROUP_FIREBASE = "com.google.firebase";
    public static final String MODULE_VERSION = "9.0.0";
    public static transient /* synthetic */ boolean __$stMC;
    private static String targetVersion;
    private static String targetVersionRaw;
    private transient /* synthetic */ MetaClass metaClass = $getStaticMetaClass();

    /* compiled from: GoogleServicesPlugin.groovy */
    enum PluginType implements GroovyObject {
        ;
        
        public static final PluginType MAX_VALUE = null;
        public static final PluginType MIN_VALUE = null;

        /* access modifiers changed from: public */
        PluginType(LinkedHashMap __namedArgs) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            this.metaClass = $getStaticMetaClass();
            if (ScriptBytecodeAdapter.compareEqual(__namedArgs, null)) {
                throw ((Throwable) $getCallSiteArray[0].callConstructor(IllegalArgumentException.class, "One of the enum constants for enum com.google.gms.googleservices.GoogleServicesPlugin$PluginType was initialized with null. Please use a non-null value or define your own constructor."));
            }
            $getCallSiteArray[1].callStatic(ImmutableASTTransformation.class, this, __namedArgs);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure1 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure1.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "APPLICATION";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._apply_closure1.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _apply_closure1(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall(null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure2 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure2.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "LIBRARY";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._apply_closure2.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _apply_closure2(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall(null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure3 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure3.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "FEATURE";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._apply_closure3.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _apply_closure3(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall(null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _apply_closure4 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure4.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "addDependency";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._apply_closure4.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _apply_closure4(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall() {
            $getCallSiteArray();
            return doCall(null);
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get());
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure5 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure5.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._setupPlugin_closure5.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _setupPlugin_closure5(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure6 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure6.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._setupPlugin_closure6.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _setupPlugin_closure6(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    class _setupPlugin_closure7 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure7.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0 == null) goto L_0x000e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
            /*
                java.lang.ref.SoftReference r0 = $callSiteArray
                if (r0 == 0) goto L_0x000e
                java.lang.ref.SoftReference r0 = $callSiteArray
                java.lang.Object r0 = r0.get()
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
                if (r0 != 0) goto L_0x0019
            L_0x000e:
                org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
                java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
                r1.<init>(r0)
                $callSiteArray = r1
            L_0x0019:
                org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin._setupPlugin_closure7.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
        }

        public _setupPlugin_closure7(Object _outerInstance, Object _thisObject, Reference project2) {
            $getCallSiteArray();
            super(_outerInstance, _thisObject);
            this.project = project2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callCurrent(this, this.project.get(), variant);
        }
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] strArr = new String[170];
        $createCallSiteArray_1(strArr);
        return new CallSiteArray(GoogleServicesPlugin.class, strArr);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[]] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.String[], code=null, for r2v0, types: [java.lang.String[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static /* synthetic */ void $createCallSiteArray_1(java.lang.String[] r2) {
        /*
            r0 = 0
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 1
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 2
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 3
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 4
            java.lang.String r1 = "addDependency"
            r2[r0] = r1
            r0 = 5
            java.lang.String r1 = "setupPlugin"
            r2[r0] = r1
            r0 = 6
            java.lang.String r1 = "APPLICATION"
            r2[r0] = r1
            r0 = 7
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 8
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 9
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 10
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 11
            java.lang.String r1 = "addDependency"
            r2[r0] = r1
            r0 = 12
            java.lang.String r1 = "setupPlugin"
            r2[r0] = r1
            r0 = 13
            java.lang.String r1 = "LIBRARY"
            r2[r0] = r1
            r0 = 14
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 15
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 16
            java.lang.String r1 = "hasPlugin"
            r2[r0] = r1
            r0 = 17
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 18
            java.lang.String r1 = "addDependency"
            r2[r0] = r1
            r0 = 19
            java.lang.String r1 = "setupPlugin"
            r2[r0] = r1
            r0 = 20
            java.lang.String r1 = "FEATURE"
            r2[r0] = r1
            r0 = 21
            java.lang.String r1 = "showWarningForPluginLocation"
            r2[r0] = r1
            r0 = 22
            java.lang.String r1 = "withId"
            r2[r0] = r1
            r0 = 23
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 24
            java.lang.String r1 = "withId"
            r2[r0] = r1
            r0 = 25
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 26
            java.lang.String r1 = "withId"
            r2[r0] = r1
            r0 = 27
            java.lang.String r1 = "plugins"
            r2[r0] = r1
            r0 = 28
            java.lang.String r1 = "afterEvaluate"
            r2[r0] = r1
            r0 = 29
            java.lang.String r1 = "warn"
            r2[r0] = r1
            r0 = 30
            java.lang.String r1 = "getLogger"
            r2[r0] = r1
            r0 = 31
            java.lang.String r1 = "split"
            r2[r0] = r1
            r0 = 32
            java.lang.String r1 = "split"
            r2[r0] = r1
            r0 = 33
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 34
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 35
            java.lang.String r1 = "valueOf"
            r2[r0] = r1
            r0 = 36
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 37
            java.lang.String r1 = "valueOf"
            r2[r0] = r1
            r0 = 38
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 39
            java.lang.String r1 = "next"
            r2[r0] = r1
            r0 = 40
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 41
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 42
            java.lang.String r1 = "valueOf"
            r2[r0] = r1
            r0 = 43
            java.lang.String r1 = "valueOf"
            r2[r0] = r1
            r0 = 44
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 45
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 46
            java.lang.String r1 = "findTargetVersion"
            r2[r0] = r1
            r0 = 47
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 48
            java.lang.String r1 = "split"
            r2[r0] = r1
            r0 = 49
            java.lang.String r1 = "checkMinimumVersion"
            r2[r0] = r1
            r0 = 50
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 51
            java.lang.String r1 = "dependencies"
            r2[r0] = r1
            r0 = 52
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 53
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 54
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 55
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 56
            java.lang.String r1 = "<$constructor$>"
            r2[r0] = r1
            r0 = 57
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 58
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 59
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 60
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 61
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 62
            java.lang.String r1 = "dependencies"
            r2[r0] = r1
            r0 = 63
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 64
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 65
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 66
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 67
            java.lang.String r1 = "<$constructor$>"
            r2[r0] = r1
            r0 = 68
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 69
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 70
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 71
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 72
            java.lang.String r1 = "getConfigurations"
            r2[r0] = r1
            r0 = 73
            java.lang.String r1 = "iterator"
            r2[r0] = r1
            r0 = 74
            java.lang.String r1 = "getDependencies"
            r2[r0] = r1
            r0 = 75
            java.lang.String r1 = "iterator"
            r2[r0] = r1
            r0 = 76
            java.lang.String r1 = "equalsIgnoreCase"
            r2[r0] = r1
            r0 = 77
            java.lang.String r1 = "getGroup"
            r2[r0] = r1
            r0 = 78
            java.lang.String r1 = "equalsIgnoreCase"
            r2[r0] = r1
            r0 = 79
            java.lang.String r1 = "getGroup"
            r2[r0] = r1
            r0 = 80
            java.lang.String r1 = "getVersion"
            r2[r0] = r1
            r0 = 81
            java.lang.String r1 = "warn"
            r2[r0] = r1
            r0 = 82
            java.lang.String r1 = "getLogger"
            r2[r0] = r1
            r0 = 83
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 84
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 85
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 86
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 87
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 88
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 89
            java.lang.String r1 = "showWarningForPluginLocation"
            r2[r0] = r1
            r0 = 90
            java.lang.String r1 = "APPLICATION"
            r2[r0] = r1
            r0 = 91
            java.lang.String r1 = "all"
            r2[r0] = r1
            r0 = 92
            java.lang.String r1 = "applicationVariants"
            r2[r0] = r1
            r0 = 93
            java.lang.String r1 = "android"
            r2[r0] = r1
            r0 = 94
            java.lang.String r1 = "LIBRARY"
            r2[r0] = r1
            r0 = 95
            java.lang.String r1 = "all"
            r2[r0] = r1
            r0 = 96
            java.lang.String r1 = "libraryVariants"
            r2[r0] = r1
            r0 = 97
            java.lang.String r1 = "android"
            r2[r0] = r1
            r0 = 98
            java.lang.String r1 = "FEATURE"
            r2[r0] = r1
            r0 = 99
            java.lang.String r1 = "all"
            r2[r0] = r1
            r0 = 100
            java.lang.String r1 = "featureVariants"
            r2[r0] = r1
            r0 = 101(0x65, float:1.42E-43)
            java.lang.String r1 = "android"
            r2[r0] = r1
            r0 = 102(0x66, float:1.43E-43)
            java.lang.String r1 = "dirName"
            r2[r0] = r1
            r0 = 103(0x67, float:1.44E-43)
            java.lang.String r1 = "split"
            r2[r0] = r1
            r0 = 104(0x68, float:1.46E-43)
            java.lang.String r1 = "<$constructor$>"
            r2[r0] = r1
            r0 = 105(0x69, float:1.47E-43)
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 106(0x6a, float:1.49E-43)
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 107(0x6b, float:1.5E-43)
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 108(0x6c, float:1.51E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 109(0x6d, float:1.53E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 110(0x6e, float:1.54E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 111(0x6f, float:1.56E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 112(0x70, float:1.57E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 113(0x71, float:1.58E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 114(0x72, float:1.6E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 115(0x73, float:1.61E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 116(0x74, float:1.63E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 117(0x75, float:1.64E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 118(0x76, float:1.65E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 119(0x77, float:1.67E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 120(0x78, float:1.68E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 121(0x79, float:1.7E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 122(0x7a, float:1.71E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 123(0x7b, float:1.72E-43)
            java.lang.String r1 = "capitalize"
            r2[r0] = r1
            r0 = 124(0x7c, float:1.74E-43)
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 125(0x7d, float:1.75E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 126(0x7e, float:1.77E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 127(0x7f, float:1.78E-43)
            java.lang.String r1 = "getAt"
            r2[r0] = r1
            r0 = 128(0x80, float:1.794E-43)
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 129(0x81, float:1.81E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 130(0x82, float:1.82E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 131(0x83, float:1.84E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 132(0x84, float:1.85E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 133(0x85, float:1.86E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 134(0x86, float:1.88E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 135(0x87, float:1.89E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 136(0x88, float:1.9E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 137(0x89, float:1.92E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 138(0x8a, float:1.93E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 139(0x8b, float:1.95E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 140(0x8c, float:1.96E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 141(0x8d, float:1.98E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 142(0x8e, float:1.99E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 143(0x8f, float:2.0E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 144(0x90, float:2.02E-43)
            java.lang.String r1 = "capitalize"
            r2[r0] = r1
            r0 = 145(0x91, float:2.03E-43)
            java.lang.String r1 = "length"
            r2[r0] = r1
            r0 = 146(0x92, float:2.05E-43)
            java.lang.String r1 = "add"
            r2[r0] = r1
            r0 = 147(0x93, float:2.06E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 148(0x94, float:2.07E-43)
            java.lang.String r1 = "lineSeparator"
            r2[r0] = r1
            r0 = 149(0x95, float:2.09E-43)
            java.lang.String r1 = "iterator"
            r2[r0] = r1
            r0 = 150(0x96, float:2.1E-43)
            java.lang.String r1 = "file"
            r2[r0] = r1
            r0 = 151(0x97, float:2.12E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 152(0x98, float:2.13E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 153(0x99, float:2.14E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 154(0x9a, float:2.16E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 155(0x9b, float:2.17E-43)
            java.lang.String r1 = "getPath"
            r2[r0] = r1
            r0 = 156(0x9c, float:2.19E-43)
            java.lang.String r1 = "lineSeparator"
            r2[r0] = r1
            r0 = 157(0x9d, float:2.2E-43)
            java.lang.String r1 = "isFile"
            r2[r0] = r1
            r0 = 158(0x9e, float:2.21E-43)
            java.lang.String r1 = "file"
            r2[r0] = r1
            r0 = 159(0x9f, float:2.23E-43)
            java.lang.String r1 = "plus"
            r2[r0] = r1
            r0 = 160(0xa0, float:2.24E-43)
            java.lang.String r1 = "getPath"
            r2[r0] = r1
            r0 = 161(0xa1, float:2.26E-43)
            java.lang.String r1 = "file"
            r2[r0] = r1
            r0 = 162(0xa2, float:2.27E-43)
            java.lang.String r1 = "buildDir"
            r2[r0] = r1
            r0 = 163(0xa3, float:2.28E-43)
            java.lang.String r1 = "dirName"
            r2[r0] = r1
            r0 = 164(0xa4, float:2.3E-43)
            java.lang.String r1 = "create"
            r2[r0] = r1
            r0 = 165(0xa5, float:2.31E-43)
            java.lang.String r1 = "tasks"
            r2[r0] = r1
            r0 = 166(0xa6, float:2.33E-43)
            java.lang.String r1 = "capitalize"
            r2[r0] = r1
            r0 = 167(0xa7, float:2.34E-43)
            java.lang.String r1 = "name"
            r2[r0] = r1
            r0 = 168(0xa8, float:2.35E-43)
            java.lang.String r1 = "applicationId"
            r2[r0] = r1
            r0 = 169(0xa9, float:2.37E-43)
            java.lang.String r1 = "registerResGeneratingTask"
            r2[r0] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin.$createCallSiteArray_1(java.lang.String[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r0 == null) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static /* synthetic */ org.codehaus.groovy.runtime.callsite.CallSite[] $getCallSiteArray() {
        /*
            java.lang.ref.SoftReference r0 = $callSiteArray
            if (r0 == 0) goto L_0x000e
            java.lang.ref.SoftReference r0 = $callSiteArray
            java.lang.Object r0 = r0.get()
            org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = (org.codehaus.groovy.runtime.callsite.CallSiteArray) r0
            if (r0 != 0) goto L_0x0019
        L_0x000e:
            org.codehaus.groovy.runtime.callsite.CallSiteArray r0 = $createCallSiteArray()
            java.lang.ref.SoftReference r1 = new java.lang.ref.SoftReference
            r1.<init>(r0)
            $callSiteArray = r1
        L_0x0019:
            org.codehaus.groovy.runtime.callsite.CallSite[] r0 = r0.array
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gms.googleservices.GoogleServicesPlugin.$getCallSiteArray():org.codehaus.groovy.runtime.callsite.CallSite[]");
    }

    public GoogleServicesPlugin() {
        $getCallSiteArray();
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ MetaClass $getStaticMetaClass() {
        if (getClass() != GoogleServicesPlugin.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            classInfo = ClassInfo.getClassInfo(getClass());
            $staticClassInfo = classInfo;
        }
        return classInfo.getMetaClass();
    }

    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass2 = this.metaClass;
        if (metaClass2 != null) {
            return metaClass2;
        }
        this.metaClass = $getStaticMetaClass();
        return this.metaClass;
    }

    public /* synthetic */ Object getProperty(String str) {
        return getMetaClass().getProperty(this, str);
    }

    public /* synthetic */ Object invokeMethod(String str, Object obj) {
        return getMetaClass().invokeMethod(this, str, obj);
    }

    public /* synthetic */ void setMetaClass(MetaClass metaClass2) {
        this.metaClass = metaClass2;
    }

    public /* synthetic */ void setProperty(String str, Object obj) {
        getMetaClass().setProperty(this, str, obj);
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, GoogleServicesPlugin.class, this, ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public void apply(Project project) {
        boolean z;
        boolean z2;
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty((Project) project2.get()), "android")) || DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty((Project) project2.get()), "com.android.application"))) {
            $getCallSiteArray[4].callCurrent(this, (Project) project2.get());
            $getCallSiteArray[5].callCurrent(this, (Project) project2.get(), $getCallSiteArray[6].callGetProperty(PluginType.class));
            return;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty((Project) project2.get()), "android-library")) || DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call($getCallSiteArray[10].callGetProperty((Project) project2.get()), "com.android.library"))) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            $getCallSiteArray[11].callCurrent(this, (Project) project2.get());
            $getCallSiteArray[12].callCurrent(this, (Project) project2.get(), $getCallSiteArray[13].callGetProperty(PluginType.class));
            return;
        }
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call($getCallSiteArray[15].callGetProperty((Project) project2.get()), "android-feature")) || DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call($getCallSiteArray[17].callGetProperty((Project) project2.get()), "com.android.feature"))) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            $getCallSiteArray[18].callCurrent(this, (Project) project2.get());
            $getCallSiteArray[19].callCurrent(this, (Project) project2.get(), $getCallSiteArray[20].callGetProperty(PluginType.class));
            return;
        }
        $getCallSiteArray[21].callCurrent(this, (Project) project2.get());
        $getCallSiteArray[22].call($getCallSiteArray[23].callGetProperty((Project) project2.get()), "android", new _apply_closure1(this, this, project2));
        $getCallSiteArray[24].call($getCallSiteArray[25].callGetProperty((Project) project2.get()), "android-library", new _apply_closure2(this, this, project2));
        $getCallSiteArray[26].call($getCallSiteArray[27].callGetProperty((Project) project2.get()), "android-feature", new _apply_closure3(this, this, project2));
        $getCallSiteArray[28].call((Project) project2.get(), new _apply_closure4(this, this, project2));
    }

    private void showWarningForPluginLocation(Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[29].call($getCallSiteArray[30].call(project), "please apply google-services plugin at the bottom of the build file.");
    }

    private boolean checkMinimumVersion() {
        boolean z;
        boolean z2;
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        String[] subTargetVersions = (String[]) ScriptBytecodeAdapter.castToType($getCallSiteArray[31].call(targetVersion, "\\."), String[].class);
        String[] subMinimumVersions = (String[]) ScriptBytecodeAdapter.castToType($getCallSiteArray[32].call(MINIMUM_VERSION, "\\."), String[].class);
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            int i = 0;
            while (true) {
                if (!ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i), $getCallSiteArray[40].callGetProperty(subTargetVersions)) || !ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i), $getCallSiteArray[41].callGetProperty(subMinimumVersions))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    break;
                }
                Integer subTargetVersion = (Integer) ScriptBytecodeAdapter.castToType($getCallSiteArray[42].call(Integer.class, BytecodeInterface8.objectArrayGet(subTargetVersions, i)), Integer.class);
                Integer subMinimumVersion = (Integer) ScriptBytecodeAdapter.castToType($getCallSiteArray[43].call(Integer.class, BytecodeInterface8.objectArrayGet(subMinimumVersions, i)), Integer.class);
                if (ScriptBytecodeAdapter.compareGreaterThan(subTargetVersion, subMinimumVersion)) {
                    return true;
                }
                if (ScriptBytecodeAdapter.compareLessThan(subTargetVersion, subMinimumVersion)) {
                    return false;
                }
                i++;
            }
        } else {
            int i2 = 0;
            while (true) {
                if (!ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i2), $getCallSiteArray[33].callGetProperty(subTargetVersions)) || !ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i2), $getCallSiteArray[34].callGetProperty(subMinimumVersions))) {
                    z = false;
                } else {
                    z = true;
                }
                if (!z) {
                    break;
                }
                Integer subTargetVersion2 = (Integer) ScriptBytecodeAdapter.castToType($getCallSiteArray[35].call(Integer.class, $getCallSiteArray[36].call(subTargetVersions, Integer.valueOf(i2))), Integer.class);
                Integer subMinimumVersion2 = (Integer) ScriptBytecodeAdapter.castToType($getCallSiteArray[37].call(Integer.class, $getCallSiteArray[38].call(subMinimumVersions, Integer.valueOf(i2))), Integer.class);
                if (ScriptBytecodeAdapter.compareGreaterThan(subTargetVersion2, subMinimumVersion2)) {
                    return true;
                }
                if (ScriptBytecodeAdapter.compareLessThan(subTargetVersion2, subMinimumVersion2)) {
                    return false;
                }
                i2 = DefaultTypeTransformation.intUnbox($getCallSiteArray[39].call(Integer.valueOf(i2)));
            }
        }
        return ScriptBytecodeAdapter.compareGreaterThanEqual($getCallSiteArray[44].callGetProperty(subTargetVersions), $getCallSiteArray[45].callGetProperty(subMinimumVersions));
    }

    private void addDependency(Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        targetVersionRaw = ShortTypeHandling.castToString($getCallSiteArray[46].callCurrent(this, project));
        targetVersion = ShortTypeHandling.castToString($getCallSiteArray[47].call($getCallSiteArray[48].call(targetVersionRaw, "-"), Integer.valueOf(0)));
        if (__$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[49].callCurrent(this))) {
                $getCallSiteArray[50].call($getCallSiteArray[51].callGetProperty(project), "compile", $getCallSiteArray[52].call($getCallSiteArray[53].call($getCallSiteArray[54].call($getCallSiteArray[55].call(MODULE_GROUP_FIREBASE, ":"), MODULE_CORE), ":"), targetVersionRaw));
                return;
            }
            throw ((Throwable) $getCallSiteArray[56].callConstructor(GradleException.class, $getCallSiteArray[57].call($getCallSiteArray[58].call($getCallSiteArray[59].call($getCallSiteArray[60].call("Version: ", targetVersionRaw), " is lower than the minimum version ("), MINIMUM_VERSION), ") required for google-services plugin.")));
        } else if (checkMinimumVersion()) {
            $getCallSiteArray[61].call($getCallSiteArray[62].callGetProperty(project), "compile", $getCallSiteArray[63].call($getCallSiteArray[64].call($getCallSiteArray[65].call($getCallSiteArray[66].call(MODULE_GROUP_FIREBASE, ":"), MODULE_CORE), ":"), targetVersionRaw));
        } else {
            throw ((Throwable) $getCallSiteArray[67].callConstructor(GradleException.class, $getCallSiteArray[68].call($getCallSiteArray[69].call($getCallSiteArray[70].call($getCallSiteArray[71].call("Version: ", targetVersionRaw), " is lower than the minimum version ("), MINIMUM_VERSION), ") required for google-services plugin.")));
        }
    }

    private String findTargetVersion(Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object configurations = $getCallSiteArray[72].call(project);
        if (ScriptBytecodeAdapter.compareEqual(configurations, null)) {
            return ShortTypeHandling.castToString(null);
        }
        Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[73].call(configurations), Iterator.class);
        while (it.hasNext()) {
            Object configuration = it.next();
            if (!ScriptBytecodeAdapter.compareEqual(configuration, null)) {
                Object dependencies = $getCallSiteArray[74].call(configuration);
                if (!ScriptBytecodeAdapter.compareEqual(dependencies, null)) {
                    Iterator it2 = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[75].call(dependencies), Iterator.class);
                    while (it2.hasNext()) {
                        Object dependency = it2.next();
                        if (!ScriptBytecodeAdapter.compareEqual(dependency, null)) {
                            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[76].call(MODULE_GROUP, $getCallSiteArray[77].call(dependency))) || DefaultTypeTransformation.booleanUnbox($getCallSiteArray[78].call(MODULE_GROUP_FIREBASE, $getCallSiteArray[79].call(dependency)))) {
                                return ShortTypeHandling.castToString($getCallSiteArray[80].call(dependency));
                            }
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        $getCallSiteArray[81].call($getCallSiteArray[82].call(project), $getCallSiteArray[83].call($getCallSiteArray[84].call($getCallSiteArray[85].call($getCallSiteArray[86].call($getCallSiteArray[87].call($getCallSiteArray[88].call("google-services plugin could not detect any version for ", MODULE_GROUP), " or "), MODULE_GROUP_FIREBASE), ", default version: "), MODULE_VERSION), " will be used."));
        $getCallSiteArray[89].callCurrent(this, project);
        return MODULE_VERSION;
    }

    private void setupPlugin(Project project, PluginType pluginType) {
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[90].callGetProperty(PluginType.class))) {
            $getCallSiteArray[91].call($getCallSiteArray[92].callGetProperty($getCallSiteArray[93].callGetProperty((Project) project2.get())), new _setupPlugin_closure5(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[94].callGetProperty(PluginType.class))) {
            $getCallSiteArray[95].call($getCallSiteArray[96].callGetProperty($getCallSiteArray[97].callGetProperty((Project) project2.get())), new _setupPlugin_closure6(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[98].callGetProperty(PluginType.class))) {
            $getCallSiteArray[99].call($getCallSiteArray[100].callGetProperty($getCallSiteArray[101].callGetProperty((Project) project2.get())), new _setupPlugin_closure7(this, this, project2));
        }
    }

    private static void handleVariant(Project project, Object variant) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        File quickstartFile = null;
        String[] variantTokens = (String[]) ScriptBytecodeAdapter.castToType($getCallSiteArray[103].call(ShortTypeHandling.castToString(new GStringImpl(new Object[]{$getCallSiteArray[102].callGetProperty(variant)}, new String[]{"", ""})), "/"), String[].class);
        List fileLocation = (List) ScriptBytecodeAdapter.castToType($getCallSiteArray[104].callConstructor(ArrayList.class), List.class);
        if (!BytecodeInterface8.isOrigInt() || !BytecodeInterface8.isOrigZ() || __$stMC || BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[105].callGetProperty(variantTokens), Integer.valueOf(2))) {
                String flavorName = ShortTypeHandling.castToString($getCallSiteArray[106].call(variantTokens, Integer.valueOf(0)));
                String buildType = ShortTypeHandling.castToString($getCallSiteArray[107].call(variantTokens, Integer.valueOf(1)));
                $getCallSiteArray[108].call(fileLocation, $getCallSiteArray[109].call($getCallSiteArray[110].call($getCallSiteArray[111].call("src/", flavorName), "/"), buildType));
                $getCallSiteArray[112].call(fileLocation, $getCallSiteArray[113].call($getCallSiteArray[114].call($getCallSiteArray[115].call("src/", buildType), "/"), flavorName));
                $getCallSiteArray[116].call(fileLocation, $getCallSiteArray[117].call("src/", flavorName));
                $getCallSiteArray[118].call(fileLocation, $getCallSiteArray[119].call("src/", buildType));
                $getCallSiteArray[120].call(fileLocation, $getCallSiteArray[121].call($getCallSiteArray[122].call("src/", flavorName), $getCallSiteArray[123].call(buildType)));
            } else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[124].callGetProperty(variantTokens), Integer.valueOf(1))) {
                $getCallSiteArray[125].call(fileLocation, $getCallSiteArray[126].call("src/", $getCallSiteArray[127].call(variantTokens, Integer.valueOf(0))));
            }
        } else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[128].callGetProperty(variantTokens), Integer.valueOf(2))) {
            String flavorName2 = ShortTypeHandling.castToString(BytecodeInterface8.objectArrayGet(variantTokens, 0));
            String buildType2 = ShortTypeHandling.castToString(BytecodeInterface8.objectArrayGet(variantTokens, 1));
            $getCallSiteArray[129].call(fileLocation, $getCallSiteArray[130].call($getCallSiteArray[131].call($getCallSiteArray[132].call("src/", flavorName2), "/"), buildType2));
            $getCallSiteArray[133].call(fileLocation, $getCallSiteArray[134].call($getCallSiteArray[135].call($getCallSiteArray[136].call("src/", buildType2), "/"), flavorName2));
            $getCallSiteArray[137].call(fileLocation, $getCallSiteArray[138].call("src/", flavorName2));
            $getCallSiteArray[139].call(fileLocation, $getCallSiteArray[140].call("src/", buildType2));
            $getCallSiteArray[141].call(fileLocation, $getCallSiteArray[142].call($getCallSiteArray[143].call("src/", flavorName2), $getCallSiteArray[144].call(buildType2)));
        } else if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[145].callGetProperty(variantTokens), Integer.valueOf(1))) {
            $getCallSiteArray[146].call(fileLocation, $getCallSiteArray[147].call("src/", BytecodeInterface8.objectArrayGet(variantTokens, 0)));
        }
        String searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[148].call(System.class));
        Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[149].call(fileLocation), Iterator.class);
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File jsonFile = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[150].call(project, $getCallSiteArray[151].call($getCallSiteArray[152].call(ShortTypeHandling.castToString(it.next()), "/"), JSON_FILE_NAME)), File.class);
            searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[153].call($getCallSiteArray[154].call(searchedLocation, $getCallSiteArray[155].call(jsonFile)), $getCallSiteArray[156].call(System.class)));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[157].call(jsonFile))) {
                quickstartFile = jsonFile;
                break;
            }
        }
        if (ScriptBytecodeAdapter.compareEqual(quickstartFile, null)) {
            quickstartFile = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[158].call(project, JSON_FILE_NAME), File.class);
            searchedLocation = ShortTypeHandling.castToString($getCallSiteArray[159].call(searchedLocation, $getCallSiteArray[160].call(quickstartFile)));
        }
        Project project2 = project;
        File outputDir = (File) ScriptBytecodeAdapter.castToType($getCallSiteArray[161].call(project2, new GStringImpl(new Object[]{$getCallSiteArray[162].callGetProperty(project), $getCallSiteArray[163].callGetProperty(variant)}, new String[]{"", "/generated/res/google-services/", ""})), File.class);
        GoogleServicesTask task = (GoogleServicesTask) ScriptBytecodeAdapter.castToType($getCallSiteArray[164].call($getCallSiteArray[165].callGetProperty(project), new GStringImpl(new Object[]{$getCallSiteArray[166].call($getCallSiteArray[167].callGetProperty(variant))}, new String[]{"process", "GoogleServices"}), GoogleServicesTask.class), GoogleServicesTask.class);
        ScriptBytecodeAdapter.setProperty(quickstartFile, null, task, "quickstartFile");
        ScriptBytecodeAdapter.setProperty(outputDir, null, task, "intermediateDir");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[168].callGetProperty(variant), null, task, "packageName");
        ScriptBytecodeAdapter.setProperty(MODULE_GROUP, null, task, "moduleGroup");
        ScriptBytecodeAdapter.setProperty(MODULE_GROUP_FIREBASE, null, task, "moduleGroupFirebase");
        ScriptBytecodeAdapter.setProperty(targetVersionRaw, null, task, "moduleVersion");
        $getCallSiteArray[169].call(variant, task, outputDir);
        ScriptBytecodeAdapter.setProperty(searchedLocation, null, task, "searchedLocation");
    }
}
