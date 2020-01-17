package ch.hearc.minigolf.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.ui.adapters.MinigolfsRecyclerAdapter
import ch.hearc.minigolf.utilities.InjectorUtils
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel

class MinigolfsFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val inflaterList = inflater.inflate(R.layout.fragment_list_minigolfs, container, false)


        recyclerView = inflaterList.findViewById(R.id.rv_list_minigolfs)
        recyclerView.layoutManager = LinearLayoutManager(context)

        initRecycleView()

        return inflaterList
    }

    private fun initRecycleView() {

        val minigolfAdapter = MinigolfsRecyclerAdapter()
        recyclerView.adapter = minigolfAdapter

        val factory = InjectorUtils.provideMinigolfsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MinigolfsViewModel::class.java)

        viewModel.getMinigolfs().observe(
            this,
            androidx.lifecycle.Observer { minigolfs ->
                minigolfs?.let { minigolfAdapter.submitList(minigolfs.toList()) }
            }
        )
    }
}