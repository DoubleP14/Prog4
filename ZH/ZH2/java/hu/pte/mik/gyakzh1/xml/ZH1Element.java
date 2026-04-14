package hu.pte.mik.gyakzh1.xml;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // Ez jelenti azt, hogy csak Változókra (mezőkre) lehet rátenni
@Inherited
public @interface ZH1Element {
    String text() default "";
}