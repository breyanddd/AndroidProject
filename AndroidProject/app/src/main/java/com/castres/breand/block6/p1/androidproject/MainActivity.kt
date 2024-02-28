package com.castres.breand.block6.p1.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.castres.breand.block6.p1.androidproject.data.UserRepositoryImpl
import com.castres.breand.block6.p1.androidproject.data.model.User
import com.castres.breand.block6.p1.androidproject.presentation.UsersViewModel
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    //for retrofit
    private val viewModel by viewModels<UsersViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UsersViewModel(UserRepositoryImpl(RetrofitInstance.api))
                as T

            }
        }
    })



    /*start of new arrivals

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrivalsList: ArrayList<NewArrivals>
    private lateinit var newArrivalsAdapter :NewArrivalsAdapter

    //start of components
    private lateinit var recyclerView1: RecyclerView
    private lateinit var componentsList: ArrayList<Components>
    private lateinit var componentsAdapter: ComponentsAdapter

    //start of components
    private lateinit var recyclerView2: RecyclerView
    private lateinit var psList: ArrayList<Partnerships>
    private lateinit var psAdapter: PartnershipsAdapter */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ){
                    val userList = viewModel.users.collectAsState().value
                    val context = LocalContext.current

                    LaunchedEffect(key1 = viewModel.showErrorToastChannel){
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if (show){
                                Toast.makeText(
                                    context, "Error", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    if (userList.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(16.dp)
                        ){
                            items(userList.size){index ->
                                User(userList[index])
                                Spacer(modifier = Modifier.height(16.dp))

                            }
                        }
                    }

                }
            }
        }
        //init()
    }
}

@Composable
fun User(user: User){
    val imageState = rememberAsyncImagePainter(
        model

    )
}




    /*private fun init(){
        //new arrivals
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false )
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        newArrivalsList = ArrayList()

        addDataToList()

        newArrivalsAdapter = NewArrivalsAdapter(newArrivalsList)
        recyclerView.adapter = newArrivalsAdapter

    }

    private fun addDataToList (){
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item1"))
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item2"))
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item3"))
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item4"))
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item5"))
        newArrivalsList.add(NewArrivals(R.drawable.csd_logo, "Item6"))



        //components
        recyclerView1 = findViewById(R.id.recyclerView2)
        recyclerView1.setHasFixedSize(true)
        recyclerView1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false )
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView1)
        componentsList = ArrayList()

        addDataToList2()

        componentsAdapter = ComponentsAdapter(componentsList)
        recyclerView.adapter = componentsAdapter
    }

    private fun addDataToList2 (){
        componentsList.add(Components(R.drawable.csd_logo, "Item1"))
        componentsList.add(Components(R.drawable.csd_logo, "Item2"))
        componentsList.add(Components(R.drawable.csd_logo, "Item3"))
        componentsList.add(Components(R.drawable.csd_logo, "Item4"))
        componentsList.add(Components(R.drawable.csd_logo, "Item5"))
        componentsList.add(Components(R.drawable.csd_logo, "Item6"))

        //partnerships
        recyclerView2 = findViewById(R.id.recyclerView3)
        recyclerView2.setHasFixedSize(true)
        recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false )
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView2)
        psList = ArrayList()

        addDataToList3()

        psAdapter = PartnershipsAdapter(psList)
        recyclerView.adapter = psAdapter

    }
    private fun addDataToList3 () {
        psList.add(Partnerships(R.drawable.csd_logo, "Item1"))
        psList.add(Partnerships(R.drawable.csd_logo, "Item2"))
        psList.add(Partnerships(R.drawable.csd_logo, "Item3"))
        psList.add(Partnerships(R.drawable.csd_logo, "Item4"))
        psList.add(Partnerships(R.drawable.csd_logo, "Item5"))
        psList.add(Partnerships(R.drawable.csd_logo, "Item6"))
    }

}*/