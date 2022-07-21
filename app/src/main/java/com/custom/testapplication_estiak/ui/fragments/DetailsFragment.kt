package com.custom.testapplication_estiak.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.custom.testapplication_estiak.R
import com.custom.testapplication_estiak.databinding.FragmentDetailsBinding
import java.text.ParseException
import java.text.SimpleDateFormat


class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private var mView: View? = null
    private var mContext: Context? = null

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (mView == null) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
            mView = binding!!.root
        }

        binding!!.userNameTv.text = args.userName
        binding!!.descriptionTv.text = args.descriptopn

        try {

            val  createDate = args.createDate.replace("T"," ")
            val  createDat = createDate.replace("Z","")

            val oldFormat = "yy-MM-dd hh:mm:ss"
            val newFormat = "MM-dd-yy hh:mm:ss"

            val newDateString: String

            val sdf = SimpleDateFormat(oldFormat)
            val d = sdf.parse(createDat)
            sdf.applyPattern(newFormat)
            newDateString = sdf.format(d!!)

            binding!!.createdDateTv.text = newDateString
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        Glide.with(mContext!!) //1
            .load(args.avatar)
            .placeholder(R.drawable.user)
            .fitCenter()
            .transform(RoundedCorners(5))
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.user)
            .into(binding!!.avatarIv)

        return mView;
    }

}