package com.rebana

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

  // Mendeklarasikan variabel  soundPool  yang akan diinisialisasi nanti.
  private lateinit var soundPool: SoundPool

  // Mendeklarasikan variabel untuk menyimpan ID suara yang dimuat.
  private var takSound: Int = 0
  private var tekSound: Int = 0
  private var dumSound: Int = 0
  private var crekSound: Int = 0
  private var drakSound: Int = 0
  private var bokSound: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Menyembunyikan status bar dan navigation bar
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
      window.setDecorFitsSystemWindows(false)
      window.insetsController?.hide(
          WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    }

    // Menyembunyikan status bar dan navigation bar
    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
      window.decorView.systemUiVisibility =
          (View.SYSTEM_UI_FLAG_FULLSCREEN or
              View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
              View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    val audioAttributes =
        AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

    soundPool =
        SoundPool.Builder()
            .setMaxStreams(6) // Mengatur jumlah maksimal yang dapat dimainkan secara bersamaan
            .setAudioAttributes(audioAttributes)
            .build()

    // Memuat file dari folder  res/raw  dan mengembalikan ID suara yang disimpan dalam variabel.
    takSound = soundPool.load(this, R.raw.tak, 1)
    tekSound = soundPool.load(this, R.raw.tek, 1)
    dumSound = soundPool.load(this, R.raw.darbuka3, 1)
    crekSound = soundPool.load(this, R.raw.uo1, 1)
    drakSound = soundPool.load(this, R.raw.uo3, 1)
    bokSound = soundPool.load(this, R.raw.darbuka4, 1)

    val btnTak: ShapeableImageView = findViewById(R.id.btnTak)
    val btnTek: ShapeableImageView = findViewById(R.id.btnTek)
    val btnDum: ShapeableImageView = findViewById(R.id.btnDum)
    val btnKecrek: ShapeableImageView = findViewById(R.id.btnKecrek)
    val btnDrak: ShapeableImageView = findViewById(R.id.btnDrak)
    val btnBok: ShapeableImageView = findViewById(R.id.btnBok)

    btnTak.setOnClickListener {
      // Memainkan suara dengan volume kiri dan kanan 1.0, prioritas 0, tidak diulang (loop 0 kali),
      // dan kecepatan pemutaran normal (1.0).
      soundPool.play(takSound, 1f, 1f, 0, 0, 1f)
    }

    btnTek.setOnClickListener { soundPool.play(tekSound, 1f, 1f, 0, 0, 1f) }

    btnDum.setOnClickListener { soundPool.play(dumSound, 1f, 1f, 0, 0, 1f) }

    btnKecrek.setOnClickListener { soundPool.play(crekSound, 1f, 1f, 0, 0, 1f) }

    btnDrak.setOnClickListener { soundPool.play(drakSound, 1f, 1f, 0, 0, 1f) }

    btnBok.setOnClickListener { soundPool.play(bokSound, 1f, 1f, 0, 0, 1f) }
  }

  override fun onDestroy() {
    super.onDestroy()
    soundPool.release()
  }

  override fun onBackPressed() {
    MaterialAlertDialogBuilder(this)
        .setTitle("Keluar Aplikasi")
        .setMessage("Apakah Anda yakin ingin keluar?")
        .setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
        .setNegativeButton("Tidak", null)
        .show()
  }
}
