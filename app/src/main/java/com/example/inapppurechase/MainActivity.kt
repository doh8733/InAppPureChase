package com.example.inapppurechase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.billingclient.api.*
import com.example.inapppurechase.adapter.PhotoSliderAdapter
import com.example.inapppurechase.adapter.TypeProductAdapter
import com.example.inapppurechase.model.Photos
import com.example.inapppurechase.model.TypeProduct
import java.util.*

class MainActivity : AppCompatActivity() {

    private val linearLayout: LinearLayout by lazy { findViewById<LinearLayout>(R.id.linearLayout) }
    private val vPView: ViewPager by lazy { findViewById<ViewPager>(R.id.vPView) }
    private val cardView: CardView by lazy { findViewById<CardView>(R.id.tvBuy) }
    private val rcView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcView) }
    private val tvBuy: CardView by lazy { findViewById<CardView>(R.id.tvBuy) }

    private var mListProduct : MutableList<TypeProduct> = mutableListOf()

    private var mListphoto : List<Photos> = listOf()
    private var time = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mListphoto = getSlider()
        vPView.adapter = PhotoSliderAdapter(this, mListphoto)
        autoSlideImage()
        mListProduct = getAllDataProductType()
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = TypeProductAdapter(this,mListProduct)
        buildBillingFunc()


    }

    private fun getSlider(): List<Photos>{
        val  list : MutableList<Photos> = mutableListOf()
        list.add(Photos(R.drawable.shopping1))
        list.add(Photos(R.drawable.shopping2))
        list.add(Photos(R.drawable.shopping2))

        return list
    }

    private fun autoSlideImage(){
        if (mListphoto.isEmpty()){
            return
        }
        time.schedule(object : TimerTask(){
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    var currentItem : Int = vPView.currentItem
                    val totalItem : Int = mListphoto.size -1
                    if (currentItem < totalItem){
                        currentItem++
                        vPView.currentItem = currentItem
                    }
                    else{
                        vPView.currentItem = 0
                    }
                }
            }

        },800,2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        time.cancel()
        time == null
    }

    private fun buildBillingFunc() {
        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }
        val skuList = ArrayList<String>()
        skuList.add("android.test.purchased")
        skuList.add("android.test.purchased")
        skuList.add("android.test.purchased")

        var billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        tvBuy.setOnClickListener {
            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingServiceDisconnected() {

                }

                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        val params = SkuDetailsParams.newBuilder()
                        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)

                        billingClient.querySkuDetailsAsync(params.build()) { billingResult, skudetailsList ->
                            for (skuDetail in skudetailsList!!) {
                                val flowPurchase = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetail)
                                    .build()
                                val responseCode = billingClient.launchBillingFlow(
                                    this@MainActivity,
                                    flowPurchase
                                ).responseCode
                            }

                        }
                    }
                }

            })
        }
    }



    private fun getAllDataProductType() : MutableList<TypeProduct>{
        mListProduct.add(TypeProduct(1,"T-Shirt",R.drawable.tshirt))
        mListProduct.add(TypeProduct(2,"Long Sleeve Shirt",R.drawable.longswetsshirt))
        mListProduct.add(TypeProduct(1,"T-Shirt",R.drawable.longswetsshirt))
        mListProduct.add(TypeProduct(1,"T-Shirt",R.drawable.tshirt))
        mListProduct.add(TypeProduct(1,"T-Shirt",R.drawable.tshirt))

        return mListProduct

    }
}