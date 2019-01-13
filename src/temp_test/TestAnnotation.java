package temp_test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class TestAnnotation {

	/*- ============================================================================================== */

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	@interface MarkerAnnotation {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	@interface SingleValueAnnotation {
		String os() default "Android";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	@interface MultiValueAnnotation {
		String os() default "Symbian";

		int version() default 1;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	@interface ValidateNotNull {
		int[] index();
	}

	/*- ============================================================================================== */

	@MarkerAnnotation
	public void marker() {

	}

	@SingleValueAnnotation(os = "Android")
	public void singleValue() {

	}

	@MultiValueAnnotation(os = "Android", version = 9)
	public void multiValue() {

	}

	@ValidateNotNull(index = { 0, 1 })
	public String concate(String str1, String str2) {
		return str1 + str2;
	}

	/*- ============================================================================================== */

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<TestAnnotation> cl = TestAnnotation.class;
		Method[] methods = cl.getMethods();

		Method annoMethod = cl.getMethod("singleValue");
		if (annoMethod.isAnnotationPresent(SingleValueAnnotation.class)) {
			SingleValueAnnotation anno = annoMethod.getAnnotation(SingleValueAnnotation.class);
			System.out.println(anno.os());
		}

		for (Method method : methods) {
			System.out.println("\n =============================================================");
			System.out.println("MethodName : " + method.getName());

			Annotation[] annotations = method.getAnnotations();

			for (Annotation anno : annotations) {
				System.out.println("AnnotationName : " + anno.toString());

				Class<? extends Annotation> annoType = anno.annotationType();

			}

		}
	}

}
