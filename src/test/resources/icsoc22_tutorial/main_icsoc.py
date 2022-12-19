import pandas as pd
from os import listdir
from os.path import isfile, join
import sys

pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)

# test_name = 'YelpBusinessSearch3'
# extended = True

# Load test data paths (test-cases and test-results)
# test_data_path = 'resources/test-data/' + test_name


args = sys.argv
test_data_path = args[1]

# test_data_path = "C:\\Users\\jcav\\Documents\\GitHub\\icsoc22-tutorial\\RESTest\RESTest\\target\\test-data\\SpotifyCreatePlaylist"


test_data_files =[f for f in listdir(test_data_path) if isfile(join(test_data_path, f))]

# test_data_files.remove('time.csv')

test_cases_files = [f for f in test_data_files if f.startswith('test-cases')]
test_results_files = [f for f in test_data_files if f.startswith('test-results')]

# Load and merge test data files

# Test cases
df_cases = pd.read_csv(test_data_path + '/' + test_cases_files[0], sep=',')
for i in range(1, len(test_cases_files)):
    print(test_cases_files[i])
    df = pd.read_csv(test_data_path + '/' + test_cases_files[i], sep=',')
    df_cases = pd.concat([df_cases, df])


df_cases = df_cases[['testCaseId', 'queryParameters', 'operationId', 'path', 'httpMethod', 'headerParameters', 'pathParameters', 'formParameters', 'bodyParameter']]
print('### Test cases')
print('Length: {}'.format(len(df_cases)))
print('Unique: {}'.format(df_cases['testCaseId'].nunique()))
# print(df_cases.columns)
# print(df_cases.head())

# Test results
df_results = pd.read_csv(test_data_path + '/' + test_results_files[0], sep=',')
for i in range(1, len(test_results_files)):
    df = pd.read_csv(test_data_path + '/' + test_results_files[i], sep=',')
    # print('Chunk ' + str(i))
    # print(df.head())
    df_results = pd.concat([df_results, df])


df_results = df_results[['testResultId', 'statusCode', 'passed', 'failReason', 'responseBody']]
df_results = df_results.rename(columns={'testResultId': 'testCaseId'})


# MERGE
print('### Test results')
print('Length: {}'.format(len(df_results)))
print('Unique: {}'.format(df_results['testCaseId'].nunique()))

merged_df = pd.merge(df_cases, df_results,
         how='inner',
         on='testCaseId',

         # validate='one_to_one'
         )

print(len(merged_df))
print(merged_df.head())


def convert_to_single_line(my_str):
    return " ".join(line.strip() for line in my_str.splitlines())


merged_df['responseBody'] = merged_df['responseBody'].apply(convert_to_single_line)
output_file_name = '{}.csv'.format('csvSpotify')
merged_df.to_csv('{}'.format(output_file_name), sep=',')
