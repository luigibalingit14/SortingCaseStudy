# 📘 DATASTRUCTURES — MIDTERM CASE STUDY
## Sorting Algorithms Visualizer (Java Swing GUI Application)

**Course:** Data Structures (DATASTRU)  
**Group:** GROUP 4  

### 👥 Group Members & Assigned Reporting Parts

| # | Member | Assigned Code Part |
|---|--------|-------------------|
| 1 | BACALLO, Khen Isiah R. | Part 1 — Class Structure & Program Initialization |
| 2 | BALINGIT, Luigi D. | Part 9 — Algorithm Selection & Switch Dispatch (Team Leader / Integration) |
| 3 | DUYANEN, Kryzthelle C. | Part 3 — Bubble Sort Algorithm |
| 4 | ESCARTIN, Angelica Maze Z. | Part 4 — Selection Sort Algorithm |
| 5 | JIMENEZ, Carl Kian B. | Part 5 — Insertion Sort Algorithm |
| 6 | LEONEN, Clark Kirby M. | Part 6 — Merge Sort Algorithm (Divide & Conquer) |
| 7 | NASOL, Aeron Francis L. | Part 2 — Output Helper Methods (append & formatArray) |
| 8 | PABILANI, Kizziah Aherica J. | Part 8 — Input Validation & Error Handling |
| 9 | SORCOSO, Lean Marr M. | Part 7 — GUI Design (Glassmorphism UI & Custom Controls) |
| 10 | STAGEN, Stanley Fox P. | Part 10 — Main Method, Testing & Sample Runs |

---

## I. INTRODUCTION

This project is a **GUI-based Sorting Algorithms Visualizer** built using **Java Swing** in the **Apache NetBeans IDE**. Its objective is to demonstrate **step-by-step** how four sorting algorithms (Bubble, Selection, Insertion, and Merge Sort) arrange an array — from the **original array**, through each **iteration/pass**, to the **final sorted array**.

---

## II. CASE STUDY REQUIREMENTS

- ✅ Implement sorting using: **A. Bubble Sort, B. Selection Sort, C. Insertion Sort, D. Merge Sort**
- ✅ Input the **size of the array** first
- ✅ Input **array elements** based on the given size
- ✅ Select a sorting algorithm and **display all elements from the original array, each iteration, up to the sorted array**

---

## III. TECHNOLOGIES & TOOLS USED

| Tool / Technology | Purpose |
|---|---|
| Java (JDK) | Programming language |
| Apache NetBeans IDE | Development environment (GUI Builder / drag-and-drop) |
| Java Swing | GUI components (JFrame, JTextArea, JComboBox, JButton) |
| Custom Controls | Glassmorphism UI effect (GlassPanel, ModernLabel) |
| AbsoluteLayout | Component positioning |

---

## IV. PROJECT FILE STRUCTURE

```text
SortingCaseStudy/
└─ src/
   ├─ Assests/
   │  ├─ image-mesh-gradient.png
   │  └─ visax-r9DV-EdDmWM-unsplash.jpg      (background image)
   ├─ CustomControl/
   │  ├─ GlassPanel.java                     (frosted glass panel)
   │  ├─ LogoLabel.java
   │  └─ ModernLabel.java                    (image label / background)
   └─ sortingcasestudy/
      └─ SortingFrame.java                   (main program file)
```

---

## V. UI DESIGN (Glassmorphism / Apple-Inspired)

The team utilized a **dark glassmorphism theme**:
- **GlassPanel** — translucent panel containing all input components
- **ModernLabel** — full-screen background image
- Rounded text fields, combo box, and SUBMIT button
- Read-only **Output Area** with auto-scroll for step-by-step results

---

## VI. SAMPLE RUNS (Test Results)

**Input:** Array size = `5` | Elements = `9 5 1 4 3`

### A. Bubble Sort
```text
=== BUBBLE SORT ===
Original: | 9 | 5 | 1 | 4 | 3 |
Pass 1:   | 5 | 1 | 4 | 3 | 9 |
Pass 2:   | 1 | 4 | 3 | 5 | 9 |
Pass 3:   | 1 | 3 | 4 | 5 | 9 |
Sorted:   | 1 | 3 | 4 | 5 | 9 |
```

### B. Selection Sort
```text
=== SELECTION SORT ===
Original: | 9 | 5 | 1 | 4 | 3 |
Pass 1:   | 1 | 5 | 9 | 4 | 3 |
Pass 2:   | 1 | 3 | 9 | 4 | 5 |
Pass 3:   | 1 | 3 | 4 | 9 | 5 |
Pass 4:   | 1 | 3 | 4 | 5 | 9 |
Sorted:   | 1 | 3 | 4 | 5 | 9 |
```

### C. Insertion Sort
```text
=== INSERTION SORT ===
Original: | 9 | 5 | 1 | 4 | 3 |
Pass 1:   | 5 | 9 | 1 | 4 | 3 |
Pass 2:   | 1 | 5 | 9 | 4 | 3 |
Pass 3:   | 1 | 4 | 5 | 9 | 3 |
Pass 4:   | 1 | 3 | 4 | 5 | 9 |
Sorted:   | 1 | 3 | 4 | 5 | 9 |
```

### D. Merge Sort
```text
=== MERGE SORT ===
Original: | 9 | 5 | 1 | 4 | 3 |
Merge:    | 5 | 9 | 1 | 4 | 3 |
Merge:    | 1 | 5 | 9 | 4 | 3 |
Merge:    | 1 | 5 | 9 | 3 | 4 |
Merge:    | 1 | 3 | 4 | 5 | 9 |
Sorted:   | 1 | 3 | 4 | 5 | 9 |
```

### Error Handling Tests
| Test Input | Result |
|---|---|
| Size = `0` | ⚠️ Error: Array size must be greater than 0. |
| Elements = blank | ⚠️ Error: Please enter array elements separated by spaces. |
| Size = 5, Elements = `9 5` | ⚠️ Error: Need 5 elements, but only 2 entered. |
| Size = `abc` | ⚠️ Invalid Input: Please use whole numbers only. |

---

## VII. TIME COMPLEXITY SUMMARY

| Algorithm | Best | Average | Worst | Space |
|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) |

---

## VIII. HOW TO RUN THE PROJECT

1. Open **Apache NetBeans IDE**.
2. Go to Open Project → select **SortingCaseStudy**.
3. Ensure the `CustomControl` package and `Assests` folder are complete.
4. Right-click `SortingFrame.java` → **Run File** (Shift+F6).
5. Input the array size and elements, choose an algorithm, and click **SUBMIT**.

---

## IX. CONCLUSION

Group 4 successfully demonstrated the differences between four sorting algorithms through an interactive GUI application. Each algorithm displays its **step-by-step iteration**, aiding in understanding sorting concepts. Testing also proved that the program's **input validation** and **error handling** are robust.

---
*"Program flow: Input size → Input elements → Choose algorithm → See every iteration → Sorted!"*
**— Group 4**
