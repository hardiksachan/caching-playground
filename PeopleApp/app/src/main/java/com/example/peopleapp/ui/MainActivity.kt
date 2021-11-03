package com.example.peopleapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import com.example.peopleapp.common.ProductionDispatcherProvider
import com.example.peopleapp.common.Resource
import com.example.peopleapp.data.data_source.remote.RemoteDataSourceImpl
import com.example.peopleapp.data.repository.PersonRepositoryImpl
import com.example.peopleapp.domain.models.Person
import com.example.peopleapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = PersonRepositoryImpl(
            remoteDataSource = RemoteDataSourceImpl(
                endpoint = "https://fake-people-server.herokuapp.com/people",
                dispatcherProvider = ProductionDispatcherProvider
            )
        )

        val resFlow = repo.getPeople()
        setContent {

            val res: Resource<List<Person>> =
                resFlow.collectAsState(initial = Resource.Success(emptyList(), true)).value

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Text(res.toString())
                }
            }
        }
    }

}