package com.example.inapppurechase

import android.app.Activity
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.inapppurechase.adapter.ProductAdapter
import com.example.inapppurechase.model.Product
import com.google.common.collect.ImmutableList
import kotlin.math.log

private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"

class ProductActivity : AppCompatActivity(), ProductDetailsResponseListener {
    private val linearLayout: LinearLayout by lazy { findViewById<LinearLayout>(R.id.linearLayout) }
    private val rcView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcView) }
    private val listPrd: MutableList<Product> = mutableListOf()
    private var listFind: MutableList<Product> = mutableListOf()


    private var listProductDetails = mutableListOf<ProductDetails>()
    private lateinit var billingClient: BillingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val bundle: Bundle? = intent.extras
        var id: Int = bundle?.getInt("id")!!
        val type: String = bundle.getString("type")!!


        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->

        }
        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        listFind = getAllData()
        var post: Int = -1
        for (i in listPrd.indices) {
            if (listPrd[i].type.contains(type)) {
                listFind.add(
                    Product(
                        //nho sap xep theo thu tu
                        listPrd[i].id,
                        listPrd[i].name,
                        listPrd[i].image,
                        listPrd[i].price,
                        listPrd[i].type,
                    )
                )
                post = i
            }
        }
        if (post != -1) {
            rcView.layoutManager = GridLayoutManager(this, 2)

            //su kien mua hang khi onclick
            rcView.adapter = ProductAdapter(this, listFind) { position ->
                //co the  dua vao nut bam

                    val productDetailsParamsList = listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(listProductDetails[position])
                            .build()
                    )
                    val billingflowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build()
                    val billingResult = billingClient.launchBillingFlow(this, billingflowParams)
                    Log.d("objectProduct ", "onCreate: " + position)



            }
        } else {
            Toast.makeText(this, "not found$type", Toast.LENGTH_SHORT).show()
        }

        buidingBillingClientFunc()

    }

    private fun getAllData(): MutableList<Product> {
        listPrd.add(
            Product(
                1,
                "Ao coc tay nam",
                "https://img.freepik.com/free-psd/polo-shirt-mockup-psd-men-rsquo-s-casual-business-wear_53876-141843.jpg?w=1380&t=st=1661500030~exp=1661500630~hmac=9199f5be476a9c80d7a8a8017f47d374bc70b08a01a72eb0774eb63b1f9fbfc7",
                1500F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                2,
                "Ao coc tay nam",
                "https://img.freepik.com/free-psd/apparel-with-t-shirt-tote-bag_53876-113707.jpg?w=996&t=st=1661508792~exp=1661509392~hmac=fe3f4cb7a311e875ee1c1b744a380c0c7c992ebecaea8393b7651ebd5bd3a703",
                1800F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                3,
                "Ao coc tay nam",
                "https://img.freepik.com/free-psd/apparel-with-t-shirt-tote-bag_53876-113707.jpg?w=996&t=st=1661508792~exp=1661509392~hmac=fe3f4cb7a311e875ee1c1b744a380c0c7c992ebecaea8393b7651ebd5bd3a703",
                1800F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                4,
                "Ao coc tay nam",
                "https://img.freepik.com/free-psd/polo-shirt-mockup-psd-men-rsquo-s-casual-business-wear_53876-141843.jpg?w=1380&t=st=1661500030~exp=1661500630~hmac=9199f5be476a9c80d7a8a8017f47d374bc70b08a01a72eb0774eb63b1f9fbfc7",
                15900F,
                "T-Shirt"
            )
        )

        listPrd.add(
            Product(
                5,
                getString(R.string.txt_long),
                "https://img.freepik.com/free-photo/man-navy-jacket-shorts-streetwear_53876-102182.jpg?w=740&t=st=1661508881~exp=1661509481~hmac=6cdec55cb6bcceea2d45ceb562c7f8b73d2ca1bcc1096ddb92643171af98f627",
                15800F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                6,
                getString(R.string.txt_long),
                "https://img.freepik.com/free-photo/man-navy-jacket-shorts-streetwear_53876-102182.jpg?w=740&t=st=1661508881~exp=1661509481~hmac=6cdec55cb6bcceea2d45ceb562c7f8b73d2ca1bcc1096ddb92643171af98f627",
                150000F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                7,
                getString(R.string.txt_long),
                "https://img.freepik.com/free-photo/man-navy-jacket-shorts-streetwear_53876-102182.jpg?w=740&t=st=1661508881~exp=1661509481~hmac=6cdec55cb6bcceea2d45ceb562c7f8b73d2ca1bcc1096ddb92643171af98f627",
                158800F,
                "T-Shirt"
            )
        )
        listPrd.add(
            Product(
                8,
                getString(R.string.txt_long),
                "https://img.freepik.com/free-photo/man-navy-jacket-shorts-streetwear_53876-102182.jpg?w=740&t=st=1661508881~exp=1661509481~hmac=6cdec55cb6bcceea2d45ceb562c7f8b73d2ca1bcc1096ddb92643171af98f627",
                1508880F,
                "T-Shirt"
            )
        )
        return listPrd
    }

    private fun buidingBillingClientFunc() {

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    val queryProductDetailsParams = QueryProductDetailsParams
                        .newBuilder()
                        .setProductList(
                            ImmutableList.of(
                                QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId(inapp_type_1)
                                    .setProductType(BillingClient.ProductType.INAPP)
                                    .build(),
                                QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId(inapp_type_2)
                                    .setProductType(BillingClient.ProductType.INAPP)
                                    .build(),
                                QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId(inapp_type_3)
                                    .setProductType(BillingClient.ProductType.INAPP)
                                    .build(),
                                QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId(inapp_type_4)
                                    .setProductType(BillingClient.ProductType.INAPP)
                                    .build(),
                                QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId(inapp_type_5)
                                    .setProductType(BillingClient.ProductType.INAPP)
                                    .build()

                            )

                        ).build()
                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
                        Log.d("log1", "onBillingSetupFinished: " + productDetailsList.size)
                        listProductDetails = productDetailsList
                    }
                }
            }

        })


    }


    override fun onProductDetailsResponse(p0: BillingResult, p1: MutableList<ProductDetails>) {

    }

}