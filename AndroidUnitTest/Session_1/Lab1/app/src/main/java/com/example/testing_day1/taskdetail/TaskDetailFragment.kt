
package com.example.testing_day1.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testing_day1.EventObserver
import com.example.testing_day1.R
import com.example.testing_day1.databinding.TaskdetailFragBinding
import com.example.testing_day1.tasks.DELETE_RESULT_OK
import com.example.testing_day1.util.setupRefreshLayout
import com.example.testing_day1.util.setupSnackbar
import com.google.android.material.snackbar.Snackbar

/**
 * Main UI for the task detail screen.
 */
class TaskDetailFragment : Fragment() {
    private lateinit var viewDataBinding: TaskdetailFragBinding

    private val args: TaskDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<TaskDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupFab()
        view.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
        setupNavigation()
        this.setupRefreshLayout(viewDataBinding.refreshLayout)
    }

    private fun setupNavigation() {
        viewModel.deleteTaskEvent.observe(viewLifecycleOwner, EventObserver {
            val action = TaskDetailFragmentDirections
                .actionTaskDetailFragmentToTasksFragment(/*DELETE_RESULT_OK*/)
            findNavController().navigate(action)
        })
        viewModel.editTaskEvent.observe(viewLifecycleOwner, EventObserver {
            val action = TaskDetailFragmentDirections
                .actionTaskDetailFragmentToAddEditTaskFragment(
                    args.taskId,
                    resources.getString(R.string.edit_task)
                )
            findNavController().navigate(action)
        })
    }

    private fun setupFab() {
        activity?.findViewById<View>(R.id.edit_task_fab)?.setOnClickListener {
            viewModel.editTask()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.taskdetail_frag, container, false)
        viewDataBinding = TaskdetailFragBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.taskId)

        setHasOptionsMenu(true)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteTask()
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.taskdetail_fragment_menu, menu)
    }
}
