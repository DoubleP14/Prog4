package hu.pte.mik.gyakzh1.xml;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Ez jelenti azt, hogy csak Metódusokra (függvényekre) lehet rátenni
public @interface BeforeSerialization {
}