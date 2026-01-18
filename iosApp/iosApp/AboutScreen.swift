//
//  AboutScreen.swift
//  iosApp
//
//  Created by 서준형 on 1/18/26.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutScreen()
}
