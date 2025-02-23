Jigsaw Puzzle Solver

1. Deskripsi Singkat

Program ini merupakan algoritma brute force untuk mencari solusi dari sebuah jigsaw puzzle. Program menerima input terkait luas papan puzzle, total potongan jigsaw puzzle, dan potongan-potongan jigsaw puzzlenya. Lalu, program harus dapat menentukan apakah terdapat sebuah solusi atau tidak dari puzzle tersebut, berdasarkan informasi yang diberi. Apabila iya, program akan mencetak papan yang sudah terisi penuh dengan semua jigsaw puzzle yang diberi.

2. Requirement dan Instalasi

Sebelum menjalankan program, pastikan Anda telah menginstal Java. Jika belum, silakan instal Java terlebih dahulu sesuai dengan sistem operasi yang Anda gunakan.

Instalasi Java

Windows: Download dan install Java dari Oracle atau gunakan OpenJDK.

Linux (Debian/Ubuntu): Jalankan perintah:

sudo apt update && sudo apt install default-jdk

MacOS: Install melalui Homebrew:

brew install openjdk

3. Cara Mengkompilasi dan Menjalankan Program

Clone Repository

Jika Anda belum memiliki kode program, clone repository terlebih dahulu:

git clone <repository-url>

Gantilah <repository-url> dengan URL repository tempat kode ini disimpan.

Masuk ke Folder Repository

cd <nama-folder-repo>

Gantilah <nama-folder-repo> dengan nama folder hasil clone.

Kompilasi Program

javac -d bin src/modules/Puzzle.java src/Main.java

Perintah ini akan mengompilasi file Java dan menyimpan hasil kompilasi di folder bin.

Menjalankan Program

java -cp bin Main

Setelah itu, program akan berjalan dan meminta input dari pengguna.

4. Author

Mahesa Fadhillah AndreNIM: 13523140

