package hu.pte.mik.gyakzh1.xml;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Ez jelenti azt, hogy csak Osztályokra lehet rátenni
@Inherited
public @interface ZH1Serializable {
    String text() default "";
}