//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by 서준형 on 2/1/26.
//

import SwiftUI
import shared

extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper : ObservableObject {
        let articlesViewModel: ArticlesViewModel
        @Published var articlesState: ArticlesState
        
        init() {
            articlesViewModel = ArticlesViewModel()
            articlesState = articlesViewModel.articlesState.value
        }
        
        func startObserving() {
            Task {
                for await article in articlesViewModel.articlesState {
                    self.articlesState = article
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            switch viewModel.articlesState.type {
            case ArticlesType.loading: Loader()
            case ArticlesType.success:
                if (!viewModel.articlesState.articles.isEmpty) {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.articlesState.articles, id: \.imageUrl) { article in
                                ArticleItemView(article: article)
                            }
                        }
                    }
                }
            case ArticlesType.error: ErrorMessage(message: viewModel.articlesState.error)
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Artcles")
            .font(.largeTitle)
            .fontWeight(.bold)
        
    }
}

struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
//            let encodedUrlString = article.imageUrl.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? article.imageUrl
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                        .frame(maxWidth: .infinity, alignment: .center)
                        .aspectRatio(16/9, contentMode: .fit)
                }
            }.frame(minHeight: 200)
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.description_)
            Text(article.date)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

//#Preview {
//    ArticlesScreen()
//}
