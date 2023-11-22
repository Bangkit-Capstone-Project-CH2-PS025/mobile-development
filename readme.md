# Itinergo Mobile Application

## How to run
1. Open in android studio, then run :D

## Git branching
Untuk setiap task, buat branch baru dengan spesifikasi berikut
List tipe:
- Story, untuk fitur atau use case baru
- Task, untuk bug fixing, performance improvement, refactor, dsb.
Judul: gunakan kebab case
Contoh:
- story/api-attendance
- story/page-attendance
- task/improve-sql-performance-on-xxxx-method
Setelah selesai, Pull Request ke master dan assign reviewer

## Code Styling & Repository
Sangat dimohon untuk memperhatikan hal-hal berikut:
1. Penamaan variabel, fungsi, dan kelas yang bermakna
2. Penyingkatan harus mudah ditebak dan masih terbaca
    - Misalkan, codeStylingAndRepository, terlalu panjang, disingkat menjadi: codeStyleNRepo
    - Yang Salah: csnr, cdStNrep
      3.Membuat kelas dengan pascal case (ClassName)
3. Membuat fungsi dan variable dengan camel case (fungsiDanVariabel)
4. Membuat folder dan file dengan snake case (folder_styling)
5. Membuat komponen React dan nama filenya dengan pascal case (NamaKomponen)

## Semantic Commit Message

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

## Reference
1. Please refer to [this](https://github.com/golang-standards/project-layout/tree/master/deployments) document for understanding project structure
