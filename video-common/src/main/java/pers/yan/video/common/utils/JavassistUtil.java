package pers.yan.video.common.utils;


import javassist.*;
import org.springframework.util.StringUtils;

/**
 * javassist工具类
 *
 * @author likaiyan
 * @date 2020/11/20 11:01 上午
 */
public class JavassistUtil {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    private static final String GET_PREFIX = "get";

    private static final String SET_PREFIX = "set";

    public static ClassPool getClassPool() {
        return CLASS_POOL;
    }

    /**
     * 创建类
     *
     * @param className 全路径类名
     * @return CtClass
     */
    public static CtClass makeClass(String className) {
        return CLASS_POOL.makeClass(className);
    }

    /**
     * 获取类
     *
     * @param className 全路径类名
     * @return CtClass
     * @throws NotFoundException 找不到类
     */
    public static CtClass getClass(String className) throws NotFoundException {
        return CLASS_POOL.get(className);
    }

    /**
     * 创建字段
     *
     * @param fieldClass 字段类型
     * @param fieldName  字段名
     * @param ctClass    所属类
     * @return CtField
     * @throws CannotCompileException 无法编译
     */
    public static CtField createField(CtClass fieldClass, String fieldName, CtClass ctClass) throws CannotCompileException {
        return new CtField(fieldClass, fieldName, ctClass);
    }

    /**
     * 创建字段
     *
     * @param fieldClassName 字段类型
     * @param fieldName      字段名
     * @param ctClass        所属类
     * @return CtField
     * @throws NotFoundException      找不到字段类型
     * @throws CannotCompileException 无法编译
     */
    public static CtField createField(String fieldClassName, String fieldName, CtClass ctClass) throws NotFoundException, CannotCompileException {
        return createField(CLASS_POOL.get(fieldClassName), fieldName, ctClass);
    }

    /**
     * 添加getter
     *
     * @param methodName 方法名
     * @param ctField    字段
     * @param ctClass    类
     * @throws CannotCompileException 无法编译
     */
    public static void addGetter(String methodName, CtField ctField, CtClass ctClass) throws CannotCompileException {
        ctClass.addMethod(CtNewMethod.getter(methodName, ctField));
    }

    /**
     * 添加getter
     *
     * @param ctField 字段
     * @param ctClass 类
     * @throws CannotCompileException 无法编译
     */
    public static void addGetter(CtField ctField, CtClass ctClass) throws CannotCompileException {
        String methodName = GET_PREFIX + StringUtils.capitalize(ctField.getName());
        addGetter(methodName, ctField, ctClass);
    }

    /**
     * 添加setter
     *
     * @param methodName 方法名
     * @param ctField    字段
     * @param ctClass    类
     * @throws CannotCompileException 无法编译
     */
    public static void addSetter(String methodName, CtField ctField, CtClass ctClass) throws CannotCompileException {
        ctClass.addMethod(CtNewMethod.setter(methodName, ctField));
    }

    /**
     * 添加setter
     *
     * @param ctField 字段
     * @param ctClass 类
     * @throws CannotCompileException 无法编译
     */
    public static void addSetter(CtField ctField, CtClass ctClass) throws CannotCompileException {
        String methodName = SET_PREFIX + StringUtils.capitalize(ctField.getName());
        addSetter(methodName, ctField, ctClass);
    }

    /**
     * 添加构造器
     *
     * @param signatureParams 签名
     * @param body            方法体
     * @param ctClass         类
     * @throws CannotCompileException 无法编译
     */
    public static void addConstructor(CtClass[] signatureParams, String body, CtClass ctClass) throws CannotCompileException {
        CtConstructor ctConstructor = new CtConstructor(signatureParams, ctClass);
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);
    }

    /**
     * 添加无参构造器
     *
     * @param ctClass 类
     * @throws CannotCompileException 无法编译
     */
    public static void addConstructor(CtClass ctClass) throws CannotCompileException {
        addConstructor(new CtClass[]{}, null, ctClass);
    }

}
