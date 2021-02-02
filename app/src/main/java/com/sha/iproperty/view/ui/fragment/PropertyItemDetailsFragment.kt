package com.sha.iproperty.view.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sha.iproperty.R
import com.sha.iproperty.view.adapter.PropertyMediaItemsAdapter
import com.sha.iproperty.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.property_item_details_fragment.*
import kotlinx.android.synthetic.main.view_propertydetails.*


class PropertyItemDetailsFragment : Fragment() {

    companion object {
        const val tagFragment = "PropertyItemDetailsFragment"

        fun newInstance() = PropertyItemDetailsFragment()
    }

    private val viewModel: SearchResultViewModel by lazy {
        ViewModelProviders.of(this.requireActivity()).get(SearchResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.property_item_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPropertyItem().observe(this, Observer { propertyItem ->
            val adapter = PropertyMediaItemsAdapter()
            adapter.setMediaItems(propertyItem.medias!!)

            val price = propertyItem.prices!!.first()

            propertyRecyclerView.adapter = adapter
            txtPrice.text = "${price.currency} ${price.max}"
            propertyTitle.text = propertyItem.title
            address.text = propertyItem.address?.formattedAddress
            propertyType.text = propertyItem.propertyType
            builtUpSize.text = "${propertyItem.attributes?.builtUp} ${propertyItem.attributes?.sizeUnit}"
            furnishing.text = propertyItem.attributes?.furnishing
            bedRooms.text = propertyItem.attributes?.bedroom
            bathRooms.text = propertyItem.attributes?.bathroom
            carParking.text = propertyItem.attributes?.carPark

            publishedOn.text = propertyItem.publishedAt

            landTitleValue.text = propertyItem.attributes?.landTitleType
            propertyTitleTypeValue.text = propertyItem.title
            tenureValue.text = propertyItem.attributes?.tenure
            furnishingValue.text = propertyItem.attributes?.furnishing

            toolbarShare.setOnClickListener{
                propertyItem.title?.let {
                    propertyItem.shareLink?.let {
                        sharePropertyByShareLink(propertyItem.title!!, propertyItem.shareLink!!)
                    }
                }
            }

            contact.setOnClickListener {
                propertyItem.listers?.get(0)?.contact?.phones?.get(0)?.number?.let {
                    call(it)
                }
            }

            whatsApp.setOnClickListener {
                propertyItem.listers?.get(0)?.contact?.phones?.get(0)?.number?.let {
                    whatsApp(it)
                }
            }
        })

        ivBackArrow.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun sharePropertyByShareLink(title: String, shareLink: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        share.putExtra(Intent.EXTRA_SUBJECT, title)
        share.putExtra(Intent.EXTRA_TEXT, shareLink)
        startActivity(Intent.createChooser(share, getString(R.string.share_property_details)))
    }

    private fun call(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun whatsApp(phoneNumber: String) {
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

}