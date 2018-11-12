package com.example.luissancar.recyclerviewswipe

import android.app.AlertDialog
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.appcompat.R.id.add
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.luissancar.recyclerviewswipe.R.id.recyclerViewMain
import kotlinx.android.synthetic.main.activity_datos.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Initializing an empty ArrayList to be filled with animals
    //  val datos: ArrayList<String> = ArrayList()
    val datos: ArrayList<registro> = ArrayList()

    private val p = Paint()
    private var adaptera : DatosAdapter?=null
    private var view: View? = null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Carga array  con datos
        addDatos()
        // Crea layout  vertical Layout
        recyclerViewMain.layoutManager = LinearLayoutManager(this)

        // si quieres utilizar mas de una columna
        recyclerViewMain.layoutManager = GridLayoutManager(this, 1)

        // Acede al RecyclerView Adapter y lee datos
        adaptera= DatosAdapter(datos, this)

        recyclerViewMain.adapter = adaptera


        initSwipe()




    }



    fun addDatos(){

        for (i in 1..20) {
            var reg: registro=registro("dato A " + i.toString(),"dato B " + i.toString(),"http://gcba.github.io/iconos/Iconografia_PNG/arbol.png")
            datos.add(reg)
        }

    }



    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    adaptera!!.removeItem(position)
                } else {

                    adaptera!!.removeItem(position)

                }
            }
            private fun removeView() {
                if (view!!.parent != null) {
                    (view!!.parent as ViewGroup).removeView(view)
                }
            }
/*


*/

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX > 0) {
                        p.color = Color.parseColor("#388E3C")
                        val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_edit_white)
                        val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, icon_dest, p)
                    } else {
                        p.color = Color.parseColor("#D32F2F")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.chico)
                        val icon_dest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, icon_dest, p)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewMain)
    }


}
