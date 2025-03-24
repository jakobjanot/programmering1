---
marp: true
theme: kea
paginate: true
--- 
<!-- _class: lead -->

![bg right](https://www.byggeplads.dk/sites/default/files/styles/byggeri-slideshow/public/byggeri/2013/kea-empire-campus/kea-disponering.jpg)

# INDKAPSLING

### Programmering 1
### Lektion 6

---
<!-- _class: invert -->
# Progammering 1 - lektion 6
...
4. Klasser og objekter
5. Arv og komposition
6. Indkapsling (I DAG)
7. Abstrakte klasser og interfaces
8. Polymorfi
...

<!-- Vi er i gang med at læse om objekter og klasser i -->

---
<!-- _class: invert -->
# Læringsmål - i dag

- Hvad er indkapsling
  - `public` og `private`
  - `get`ter og `set`ter metoder
- Hvorfor bruge indkapsling?
  - API
  - Refaktorering
- Øvelser i grupper - og diskussion

---
<!-- _class: rose -->

# Repetition af klasser og objekter

---

<!-- _class: rose -->

## Repetition af klasser

```java
package dk.kea.prog1.ex1;

class SavingsAccount {
  // fields
  double balance;
  double rate = 0.05;
  String owner;

  // constructor
  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  // method
  void applyInterest() {
    balance = balance * (1 + rate);
  }
}
```

<!-- 

Klasser er en måde at organisere kode på. Vi kan knytte data og metoder sammen i en klasse, så vi kan anvede dem sammen.

Klasser er en skabelon for objekter. Vi kan lave mange objekter af samme klasse med forskellige data.

Når vi laver et objekt af klassen SavingsAccount, så laver vi en konto med en saldo og en ejer. Vi kan lave mange konti med forskellige saldi og ejere.

Vi kalder det at lave et objekt af en klasse for at instansiere klassen, dvs. at lave en instans af klassen.

En klasse består af 
- fields (felter) som definerer objektets tilstand (state). Fields er variabler som gemmer data saldo'en som er et decimaltal. Da variablerne hører til objektet (instansen), kalder vi dem instansvariabler.
- metoder som definerer objektets adfærd (behavior).
- konstruktør (constructor) som initialiserer objekter af klassen.
- package statement som definerer hvilken pakke klassen hører til.

Vi kalder også feltene for instansvariabler, fordi de er unikke for hvert objekt af klassen.

I eksemplet har vi en klasse `SavingsAccount` som er en skabelon for opsparingskonti.

Klassen SavingsAccount består af felterne (fields): 
- `balance` (saldo)
- `owner` (kontoejer)

Klassen har en konstruktør (constructor) som er en metode der initialiserer objekter af klassen. Konstruktøren herover tager to parametre: balance og owner.

Herefter kan vi kalde metoden `applyInterest` på objekter af klassen SavingsAccount. Metoden tilføjer renter til saldoen.
-->

---

<!-- _class: rose -->

# Repetiton af objekter

```java
package dk.kea.prog1.ex1;

class Runner {
  public static void main(String[] args) {
    
    SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");
    
    kimsAccount.applyInterest();
    
    System.out.println(kimsAccount.balance); // => 1050
  }
}
```

<!--
Vi initialiserer en konto til Kim med en saldo på 1000 kr. Herefter tilføjer vi 5% rente til saldoen, så saldoen er 1050 kr.
-->

---


<!-- _class: blue -->

# Hvad er indkapsling

---

<!-- _class: blue -->

# DEMO

Opsparingskonto

<!--
1. Kør programmet fra InsideRunner.java
2. Prøv så koden i OutsideRunner.java
3. Hvorfor virker det ikke?
-->
---

<!-- _class: blue -->

Klassen, dens felter og metoder (og constructor) har access modifiers:
- `public`
- `private`
- `protected`
- "default" (ingen access modifier)

<!--
Default access modifier (package-private) betyder at felter og metoder er tilgængelige for klasser i samme pakke.

Det giver os problemer når vi tilgår SavingsAccount fra en anden pakke.
-->

---

<!-- _class: blue -->

# DEMO

Lad os gøre det hele `public` og se hvad der sker.

---

<!-- _class: blue -->

## Ny adfærd: Hæve penge fra kontoen

```java
package dk.kea.prog1; // NB: Ændret pakkenavn

SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");

kimsAccount.balance = kimsAccount.balance - 1200;

System.out.println(kimsAccount.balance); // => -200
```
<br>
Hvad er problemet her?

<!--
At ændre `balance` direkte er et problem fordi vi ikke kan kontrollere hvad der sker med `balance`, f.eks. at saldoen aldrig må være negativ.
-->

---

<!-- _class: blue -->

# `get`ter og `set`ter metoder:

- Konvention: `get` og `set` foran feltnavn, dvs.
  ```java
  public double getBalance() {
    return balance;
  }
  ```
  ```java
  public void setBalance(double balance) {
    this.balance = balance;
  }
  ```

<!--
getter og setter metoder er en god praksis.
Det er en konvention

Måske er det ikke meningen at vi skal ændre saldoen direkte med en setter metode?

Logikken for at hæve/indsætte penge er en naturlig adfærd for en konto og bør derfor være en metode på klassen `SavingsAccount` og ikke noget som brugeren af klassen skal tænke på.
-->
---

<!-- _class: blue -->

# DEMO

Lad os lave get og set metoder for `balance`, så saldoen aldrig kan være negativ.

<!--
1. Gør `balance` o.a. felter `private`
2. Lav en `public` getter og setter metoder for `balance` og `owner`
-->

---

<!-- _class: blue -->

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  public SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  [...]

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    if (balance < 0)
      return;
    
    this.balance = balance;
  }
}
```

---

<!-- _class: blue -->

```java
SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");

kimsAccount.setBalance(kimsAccount.getBalance() - 1200);

System.out.println(kimsAccount.getBalance()); // => 1000
```

Er der noget vi kan gøre bedre?

---

<!-- _class: blue -->

## Øvelse (20 min): At hæve/indsætte penge.

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  public SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    balance = balance * (1 + rate); 
  }

  public double getBalance() {
    return balance;
  }
}
```

<!--
Vi har nu frihed til at ændre den implementering af klassen `SavingsAccount` uden at det påvirker andre dele af programmet. Vi kan f.eks. ændre hvordan saldoen beregnes eller hvordan renten tilføjes.

--> 

---

<!-- _class: blue -->

# Diskussion af øvelse: At hæve/indsætte penge

---

<!-- _class: blue -->

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    double interest = balance * (1 + rate);
    deposit(interest);
  }

  public void withdraw(double amount) {
    if (amount > balance)
      return;
    
    setBalance(balance - amount);
  }

  public void deposit(double amount) {
    if (amount < 0)
      return;

    setBalance(balance + amount);
  }

  public double getBalance() {
    return balance;
  }

  private void setBalance(double balance) {
    if (balance < 0)
      return;
    
    this.balance = balance;
  }
}
```

---

<!-- _class: blue -->

```java
SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");

kimsAccount.withdraw(1200);
System.out.println(kimsAccount.getBalance()); // => 1000

kimsAccount.withdraw(200);
System.out.println(kimsAccount.getBalance()); // => 800
```

<br/>

## Resultat

- Mere kontrol over objektets tilstand
- Mere fleksibilitet til at ændre klassens implementering
- Mere læsbart og vedligeholdeligt kode

---

# Hvorfor bruge indkapsling?

---

# API - Application Programming Interface

- `public` metoder er klassens API - grænsefladen til klassens brugere
- `private` metoder er klassens interne implementering - skjult for klassens brugere
- Refaktorering: at ændre koden uden at ændre klassens API

<!--
Pointe: Vi kan ændre klassens interne implementering uden at påvirke klassens brugere.

At tilføje flere `private` metoder gør koden mere læsbar og vedligeholdelig, da vi kan opdele komplekse metoder i mindre dele.

Refaktorering er en teknik til at forbedre koden uden at ændre klassens API.
-->

---

# Øvelse: Refaktorering af `SavingsAccount`

- Kan vi gøre koden mere læsbar uden at ændre klassens API?

---
# Diskussion af øvelse: Refaktorering af `SavingsAccount`

<!-- Hvad har i gjort? -->

---

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    deposit(calculateYearlyInterest());
  }

  [...]

  private double calculateYearlyInterest() {
    return balance * (1 + rate);
  }
}
```

<!-- En måde at gøre koden mere læsbar er at flytte beregningen af renten til en separat metode `calculateYearlyInterest`. 

`calculateYearlyInterest` er et godt navn for metoden, da vi forstår at det er en årlig tilskrivning af rente
-->

---

<!-- _class: green -->

# Gruppearbejde (2 time) - Beregning af rente

1. Implementer daglig rente i stedet for årlig rente
2. Lav en ny klasse `CheckingAccount` med samme funktionalitet som `SavingsAccount`, der tillader overtræk (negativ saldo).
3. Implementer strafrente på overtrækket.
4. (Extra) Hvordan kan vi dele kode mellem `SavingsAccount` og `CheckingAccount`?
5. (Extra) Hvad gør access modifier `protected`?
6. (Extra) lav en `Bank`, der har funktionalitet til at tilskrive renter til flere konti.

---
<!-- _class: green -->

# Diskussion af gruppearbejde

<!-- Hvad har i lavet? -->

---

<!-- _class: invert -->

# Opsamling - hvad har vi lært?

Nævn tre ting I tager med fra i dag.

<!--
- Indkapsling er en måde at skjule data og metoder fra omverdenen
- Indkapsling giver mere kontrol over objektets tilstand
- `public` metoder er klassens API
- `private` metoder er klassens interne implementering
- Indkapsling gør koden mere læsbar og vedligeholdelig, da vi kan tilføje så mange `private` metoder som vi vil uden at påvirke klassens brugere
-->

---

<!-- _class: invert -->

# Næste gang

- Hvordan lader vi `Bank` have en liste med forskellige typer konti, dvs. `CheckingAccount`, `SavingsAccount` m.fl.?
- Hvordan sikrer vi os at alle konti har metoden `applyInterest()` til at tilføje renter?

---

<!-- _class: invert -->

# Næste gang - Abstrakte klasser og interfaces

- Hvordan kan vi bruge interfaces til at definere en kontrakt for klasser?
- Hvordan kan vi bruge abstrakte klasser til at dele kode mellem klasser?
