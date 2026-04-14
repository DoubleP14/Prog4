1. Repository létrehozása és az első commit (Master branch)
Init: VCS menü -> Create Git Repository... -> válaszd ki a projekt mappáját.

README: Hozz létre egy README.md fájlt a gyökérben, írd bele: ZH1.

Commit: Ctrl + K. Fontos: Csak a README.md mellé tegyél pipát! Üzenet: "Initial commit - README".

Ellenőrzés: Alul a Git fülön látnod kell, hogy a master branchen állsz.

2. Branch-kezelés (Develop és ZH1)
Develop létrehozása: Alul a jobb sarokban kattints a master feliratra -> New Branch -> név: develop.

ZH1 létrehozása: Most, hogy a develop-on állsz, megint New Branch -> név: ZH1.

Magyarázat: Így a ZH1 a develop-ból ágazik le, ami pedig a master-ből. Mostantól a ZH1 branchen dolgozol.

3. .gitignore és a projekt fájlok
Nyisd meg a .gitignore fájlt (ha nincs, hozz létre egyet).

Add hozzá ezt a sort: /.idea/ (ez elrejti az IntelliJ belső beállításait).

Commit: Most jelöld ki az összes többi fájlt (kivéve a .idea mappát, amit már nem is szabadna látnod) és a .gitignore-t is. Üzenet: "Add project files and gitignore".

4. Feladatok megoldása (Folyamatos munka)
Megcsinálod az 1. feladatot -> Commit (üzenet: "Task 1 - Food model implementation").

Megcsinálod a 2. feladatot -> Commit (üzenet: "Task 2 - FoodFilter validation").

És így tovább...

5. Befejezés: Merge és Tag-elés
Amikor minden feladat kész és el van commitolva a ZH1 branchen:

Váltás developra: Jobb alsó sarok -> kattints a ZH1 feliratra -> válaszd a develop-ot -> Checkout.

Merge: Most, hogy a develop-on állsz, kattints a jobb alsó sarokban a ZH1 brancshöz (a listában lesz) -> válaszd a Merge 'ZH1' into 'develop' opciót.

Tag-elés:

Kattints az alsó Git fülre, azon belül a Log-ra.

Keresd meg a legfelső (legfrissebb) commitot.

Jobb klikk a commiton -> New Tag...

Név: ZH1_2025_04_02 (ügyelj a pontos névvel, bár a dátum a feladatban 2025-ös, te használd azt, amit kértek).
