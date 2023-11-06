# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna 

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego. |

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([UC2](#uc2))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu.
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu.

**Scenariusze alternatywne:** 

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))
* 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.


## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC4](#uc4): Zamknięcie aukcji

[Kupujący](#ac2)
* [UC2](#uc2): Podbicie najwyższej oferty
* [UC3](#uc3): Odbiór produktu nabytego na aukcji
* [UC4](#uc4): Zamknięcie aukcji
* [UC5](#uc5): Przejrzenie dostępnych aukcji
* [UC6](#uc6): Potwierdzenie odbioru produktu

---
<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Podbicie najwyższej oferty

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć podbicia oferty.
2. System prosi o podanie kwoty nowej oferty.
3. [Kupujący](#ac2) podaje kwotę nowej oferty.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu nowej oferty.

**Scenariusze alternatywne:** 

4.A. Podana kwota jest zbyt mała lub niepoprawna.
* 4.A.1. System informuje o niepoprawnej oferowanej kwocie.
* 4.A.2. Przejdź do kroku 2.

4.B. Podana kwota wykracza poza dostępne środki [Kupującego](#ac2).
* 4.B.1. System informuje o zbyt dużej oferowanej kwocie.
* 4.B.2. Przejdź do kroku 2.

---

<a id="uc3"></a>
### UC3: Odbiór produktu nabytego na aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć odbioru nabytego produktu.
2. System udostępnia wszystkie dane o nabytym produkcie [Kupującemu](#ac2).

**Scenariusze alternatywne:** 

2.A. Nabytym produktem jest obiekt materialny.
* 2.A.1. System prosi o podanie danych pocztowych do wysyłki.
* 2.A.2. [Kupujący](#ac2) podaje dane pocztowe.
* 2.A.3. System informuje [Kupującego](#ac2) o nadaniu wysyłki.

2.A.2.A. Podane dane są niekompletne lub niepoprawne.
* 2.A.2.A.1 System informuje o niepoprawnych danych do wysyłki.
* 2.A.2.A.2 Przejdź do kroku 2.A.1.

---

<a id="uc4"></a>
### UC4: Zamknięcie aukcji

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**
1. System informuje wszystkich aktorów o zakończeniu aukcji.
2. System pobiera należność z konta [Kupującego](#ac2) z najwyższą ofertą i przekazuje ją [Sprzedającemu](#ac1).
3. System przekazuje posiadanie produktu do [Kupującego](#ac2) z najwyższą ofertą.

**Scenariusze alternatywne:** 

---

<a id="uc5"></a>
### UC5: Przejrzenie dostępnych aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. System prosi [Kupującego](#ac2) o podanie opcjonalnych wymagań.
2. System pokazuje [Kupującemu](#ac2) spis wszystkich aktywnych aukcji spełniających podane wymagania.

**Scenariusze alternatywne:** 

---

<a id="uc6"></a>
### UC6: Potwierdzenie odbioru produktu

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) informuje system o pomyślnym odebraniu produktu.
2. Rekord produktu jest usuwany z systemu.
3. System informuje [Kupującego](#ac2) o pomyślnym usunięciu produktu.

**Scenariusze alternatywne:** 

---

## Obiekty biznesowe (inaczej obiekty dziedzinowe lub informatyczne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupy produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.


<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujący](#ac2)ch, który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL


| Przypadek użycia                                  | Aukcja | Produkt |
| ------------------------------------------------- | ------ | ------- |
| UC1: Wystawienia produktu na aukcję               |    C   |    C    |
| UC2: Podbicie najwyższej oferty                   |    U   |    -    |
| UC3: Odbiór produktu nabytego na aukcji           |    -   |    R    |
| UC4: Zamknięcie aukcji                            |    D   |    U    |
| UC5: Przejrzenie dostępnych aukcji                |    R   |    -    |
| UC6: Potwierdzenie odbioru produktu               |    -   |    D    |
