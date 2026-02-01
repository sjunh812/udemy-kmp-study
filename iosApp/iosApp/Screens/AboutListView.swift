//
//  AboutListView.swift
//  iosApp
//
//  Created by 서준형 on 1/18/26.
//

import SwiftUI
import shared

//
//  AboutListView.swift
//  iosApp
//
//  Created by 서준형 on 1/18/26.
//

import Foundation

struct AboutListView: View {
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        let result: [RowItem] = [
            .init(
                title: "Operating system",
                subtitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device",
                subtitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subtitle: "@\(platform.density)x"
            )
        ]
        return result
    }()
    
    var body: some View {
        List(items, id: \.self) { item in
            VStack(alignment: .leading) {
                Text(item.title)
                    .font(.footnote)
                    .foregroundColor(.secondary)
                Text(item.subtitle)
                    .font(.body)
                    .foregroundColor(.primary)
            }
            .padding(.vertical, 4)
        }
    }
}

#Preview {
    AboutListView()
}
